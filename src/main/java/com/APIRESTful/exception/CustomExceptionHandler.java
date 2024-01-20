package com.APIRESTful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialException;
import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

public class CustomExceptionHandler {

    /*@ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {

        ProblemDetail errorDetail = null;

        if (ex instanceof CredentialException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
            errorDetail.setProperty("Error Detail", "Authentication Failure");
        }

        if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetail.setProperty("Error Detail", "Not Authorized");
        }

        if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetail.setProperty("Error Detail", "JWT Signature not valid");
        }

        if (ex instanceof AccountExpiredException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetail.setProperty("Error Detail", "JWT Token already expired");
        }

        return errorDetail;

    }*/

}
