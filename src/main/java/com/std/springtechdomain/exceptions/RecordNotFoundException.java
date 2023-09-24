package com.std.springtechdomain.exceptions;

public class RecordNotFoundException extends RuntimeException {
    //Default exception
    public RecordNotFoundException() {
        super();
    }
    //Met message exception
    public RecordNotFoundException(String message) {
        super(message);
    }

}
