package com.CustomerManagementMicroService.albanero.Exception;

import com.CustomerManagementMicroService.albanero.Domain.ErrorCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandler1 {

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<ErrorCreate> handleInvalidCustomerException(InvalidCustomerException exception) {
        ErrorCreate errorCreate = new ErrorCreate(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Customer"
        );
        return new ResponseEntity<>(errorCreate, HttpStatus.BAD_REQUEST);
    }


}
