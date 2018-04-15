package com.example.authproject.advices;


import com.example.authproject.responses.Response;
import com.example.authproject.utils.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Response response = new Response("", ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }
}
