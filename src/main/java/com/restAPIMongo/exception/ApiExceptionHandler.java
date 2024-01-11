package com.restAPIMongo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleException(RuntimeException runtimeException) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, runtimeException.getMessage());
        errorDetail.setProperty("Error Custom", "Test");
        return errorDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundException(NotFoundException notFoundException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        return problemDetail;
    }

}
