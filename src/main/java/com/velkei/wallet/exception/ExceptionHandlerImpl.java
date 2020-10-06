package com.velkei.wallet.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExist.class)
    public ResponseEntity<Object> emailAlreadyUsedException() {

        return new ResponseEntity<>("Az emailcímmel már regisztráltak!", HttpStatus.CONFLICT);
    }

}
