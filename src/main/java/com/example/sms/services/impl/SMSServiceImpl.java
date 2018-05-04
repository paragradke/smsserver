package com.example.sms.services.impl;


import com.example.sms.exceptions.NumberNotFoundException;
import com.example.sms.exceptions.SLAException;
import com.example.sms.exceptions.StopException;
import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
import com.example.sms.requests.SMSRequest;
import com.example.sms.services.PhoneNumberService;
import com.example.sms.services.RedisService;
import com.example.sms.services.SMSService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    private static final Logger logger = LogManager.getLogger(SMSServiceImpl.class);

    @Override
    public void processInboundSMS(final Account account, final SMSRequest smsRequest)
            throws NumberNotFoundException {
        boolean isToValid = validateNumber(account, smsRequest.getTo());
        if (!isToValid) {
            throw new NumberNotFoundException("to parameter not found");
        }
        tryCacheInjection(account, smsRequest);
    }

    @Override
    public void processOutboundSMS(final Account account, final SMSRequest smsRequest)
            throws NumberNotFoundException, StopException, SLAException {
        boolean isToValid = validateNumber(account, smsRequest.getFrom());
        logger.info("isToValid :" + isToValid);
        if (!isToValid) {
            throw new NumberNotFoundException("from parameter not found");
        }
        tryCacheValidation(account, smsRequest);
        logger.info("tryCacheValidation Done" );
        validateSLA(account, smsRequest);
        logger.info("validateSLA Done" );
    }

    private void tryCacheInjection(final Account account, final SMSRequest smsRequest) {
        final String text = smsRequest.getText();
        if (StringUtils.equals(text, "STOP")
                || StringUtils.equals(text, "STOP\r")
                || StringUtils.equals(text, "STOP\n")
                || StringUtils.equals(text, "STOP\r\n")) {
            final String from = smsRequest.getFrom();
            final String to = smsRequest.getTo();
            final String key = from+to;
            logger.info("Saving entry to cache from :"+ from+ " to :"+to+" key :"+ key);
            redisService.setCacheEntry(key, "STOP");
        }
    }

    private boolean validateNumber(final Account account, final String number) throws NumberNotFoundException {
        PhoneNumber phoneNumber = phoneNumberService.get(account, number);
        return phoneNumber != null;
    }

    private void tryCacheValidation(final Account account, final SMSRequest smsRequest) throws StopException {
        final String from = smsRequest.getFrom();
        final String to = smsRequest.getTo();
        final String key = from+to;
        final String cacheEntry = redisService.getCacheEntry(key);
        if (cacheEntry != null) {
            throw new StopException("sms from "+ smsRequest.getFrom() +
                    " to "+smsRequest.getTo()+" blocked by STOP request");
        }
    }

    private void validateSLA(final Account account, final SMSRequest smsRequest) throws SLAException {
        final String from = smsRequest.getFrom();
        final String key = from;
        Integer slaEntry = (Integer) redisService.getSLAEntry(key);
        if (slaEntry == null) {
            slaEntry = 0;
        }
        if (slaEntry.intValue() > 50) {
            throw new SLAException("“limit reached for from "+from);
        }
        slaEntry++;
        redisService.setSLAEntry(key, slaEntry);
    }
}
