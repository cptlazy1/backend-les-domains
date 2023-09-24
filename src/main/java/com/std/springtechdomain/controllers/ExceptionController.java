package com.std.springtechdomain.controllers;

import com.std.springtechdomain.exceptions.RecordNotFoundException;
import com.std.springtechdomain.exceptions.RecordTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    // Exception handler voor record not found
    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception handler voor te lange merk(brand)
    @ExceptionHandler(value = {RecordTooLongException.class})
    public ResponseEntity<Object> exception(RecordTooLongException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //Exception handler voor index out of bounds
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>("Deze index bestaat niet in de database", HttpStatus.NOT_FOUND);
    }


}
