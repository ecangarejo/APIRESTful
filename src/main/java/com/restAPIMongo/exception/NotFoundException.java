package com.restAPIMongo.exception;

public class NotFoundException extends RuntimeException {

    //private static final String DESCRIPTION = "Not Found (404)";


    public NotFoundException(String detail) {
        //super(DESCRIPTION + ". " + detail);
        super(detail);
    }

}
