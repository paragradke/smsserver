package com.example.authproject.services;

import com.example.authproject.exceptions.NumberNotFoundException;
import com.example.authproject.exceptions.SLAException;
import com.example.authproject.exceptions.StopException;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.requests.SMSRequest;
import org.springframework.stereotype.Service;

@Service
public interface SMSService {

    public void processInboundSMS(final Account account, final SMSRequest smsRequest) throws NumberNotFoundException;

    public void processOutboundSMS(final Account account, final SMSRequest smsRequest) throws NumberNotFoundException, StopException, SLAException;

}
