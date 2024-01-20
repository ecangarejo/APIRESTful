package com.APIRESTful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleException(RuntimeException runtimeException) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, runtimeException.getMessage());
        //errorDetail.setProperty("Error Custom", "Test");
        return errorDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundException(NotFoundException notFoundException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage());

        /*Map<String, String> error = new HashMap<>();
        error.put("detail", notFoundException.getMessage());

        problemDetail.setProperty("error", error);*/

        return problemDetail;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleNotValidException(BadRequestException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleNotValidException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        List<String> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();

        for (FieldError fe : fieldErrors) {
            errors.add(fe.getDefaultMessage());
        }

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleNotValidException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }*/

}
