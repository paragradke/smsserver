package com.example.sms.advices;


import com.example.sms.controllers.SMSController;
import com.example.sms.exceptions.NumberNotFoundException;
import com.example.sms.exceptions.SLAException;
import com.example.sms.exceptions.StopException;
import com.example.sms.responses.Response;
import com.example.sms.utils.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LogManager.getLogger(SMSController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException :" + ex.getLocalizedMessage());
        BindingResult result = ex.getBindingResult();
        Response response = new Response("", ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NumberNotFoundException.class)
    public ResponseEntity<Response> handleNumberNotFoundException(NumberNotFoundException ex) {
        logger.error("NumberNotFoundException :" + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        Response response = new Response("", errors);
        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StopException.class)
    public ResponseEntity<Response> handleStopException(StopException ex) {
        logger.error("StopException :" + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        Response response = new Response("", errors);
        return new ResponseEntity<Response>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(SLAException.class)
    public ResponseEntity<Response> handleStopException(SLAException ex) {
        logger.error("StopException :" + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        Response response = new Response("", errors);
        return new ResponseEntity<Response>(response, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        logger.error("Exception :" + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();
        errors.add("unknown failure");
        Response response = new Response("", errors);
        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
