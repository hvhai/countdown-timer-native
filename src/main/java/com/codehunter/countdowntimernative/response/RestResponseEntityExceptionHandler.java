package com.codehunter.countdowntimernative.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleExceptionInternalWithMsg(Exception exception, ErrorCodes errorCode, WebRequest request) {
        ResponseDTO<String> bodyOfResponse = ResponseFormatter.handleException(errorCode, Optional.ofNullable(exception.getMessage()));
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), errorCode.getStatus(), request);
    }
}
