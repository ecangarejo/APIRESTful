package com.APIRESTful.exception;

public class MalformedHeaderException extends BadRequestException {

    private static final String DESCRIPTION = "Token with wrong format";


    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
