package com.std.springtechdomain.exceptions;

public class RecordTooLongException extends RuntimeException{
    //Default exception
    public RecordTooLongException() {
        super();
    }
    //Met message exception
    public RecordTooLongException(String message) {
        super(message);
    }
}
