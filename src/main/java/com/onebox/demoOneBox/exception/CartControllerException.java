package com.onebox.demoOneBox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CartControllerException {
    @ExceptionHandler(value = {CartNotFoundException.class})
    public ResponseEntity<Object> handleEventNotFoundException(CartNotFoundException exception) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
