package com.example.sms.services;

import com.example.sms.exceptions.NumberNotFoundException;
import com.example.sms.exceptions.SLAException;
import com.example.sms.exceptions.StopException;
import com.example.sms.mappers.models.Account;
import com.example.sms.requests.SMSRequest;
import org.springframework.stereotype.Service;

@Service
public interface SMSService {

    public void processInboundSMS(final Account account, final SMSRequest smsRequest) throws NumberNotFoundException;

    public void processOutboundSMS(final Account account, final SMSRequest smsRequest) throws NumberNotFoundException, StopException, SLAException;

}
