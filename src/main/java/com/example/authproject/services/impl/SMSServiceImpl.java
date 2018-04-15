package com.example.authproject.services.impl;

import com.example.authproject.cache.CacheEntry;
import com.example.authproject.cache.SLAEntry;
import com.example.authproject.exceptions.NumberNotFoundException;
import com.example.authproject.exceptions.SLAException;
import com.example.authproject.exceptions.StopException;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.mappers.models.PhoneNumber;
import com.example.authproject.requests.SMSRequest;
import com.example.authproject.services.PhoneNumberService;
import com.example.authproject.services.RedisService;
import com.example.authproject.services.SMSService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import sun.security.provider.MD5;

@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Override
    public void processInboundSMS(final Account account, final SMSRequest smsRequest)
            throws NumberNotFoundException {
        boolean isToValid = validateNumber(account, smsRequest.getTo());
        if (isToValid) {
            throw new NumberNotFoundException("to parameter not found");
        }
        tryCacheInjection(account, smsRequest);
    }

    @Override
    public void processOutboundSMS(final Account account, final SMSRequest smsRequest)
            throws NumberNotFoundException, StopException, SLAException {
        boolean isToValid = validateNumber(account, smsRequest.getFrom());
        if (isToValid) {
            throw new NumberNotFoundException("from parameter not found");
        }
        tryCacheValidation(account, smsRequest);
        validateSLA(account, smsRequest);
    }

    private void tryCacheInjection(final Account account, final SMSRequest smsRequest) {
        final String text = smsRequest.getText();
        if (StringUtils.equals(text, "STOP")
                || StringUtils.equals(text, "STOP\r")
                || StringUtils.equals(text, "STOP\n")
                || StringUtils.equals(text, "STOP\r\n")) {
            final String from = smsRequest.getFrom();
            final String to = smsRequest.getTo();
            final String key = MD5Encoder.encode((from+to).getBytes());
            redisService.setCacheEntry(key, new CacheEntry(from, to));
        }
    }

    private boolean validateNumber(final Account account, final String number) throws NumberNotFoundException {
        PhoneNumber phoneNumber = phoneNumberService.get(account, number);
        return phoneNumber != null;
    }

    private void tryCacheValidation(final Account account, final SMSRequest smsRequest) throws StopException {
        final String from = smsRequest.getFrom();
        final String to = smsRequest.getTo();
        final String key = MD5Encoder.encode((from+to).getBytes());
        final CacheEntry cacheEntry = redisService.getCacheEntry(key);
        if (cacheEntry != null) {
            throw new StopException("sms from "+ smsRequest.getFrom() +
                    " to "+smsRequest.getTo()+" blocked by STOP request");
        }
    }

    private void validateSLA(final Account account, final SMSRequest smsRequest) throws SLAException {
        final String from = smsRequest.getFrom();
        final String key = MD5Encoder.encode(from.getBytes());
        SLAEntry slaEntry = redisService.getSLAEntry(key);
        if (slaEntry == null) {
            slaEntry = new SLAEntry(from);
        }
        if (slaEntry.getCounter() > 50) {
            throw new SLAException("“limit reached for from "+from);
        }
        slaEntry.incrementCounter();
        redisService.setSLAEntry(key, slaEntry);
    }
}
