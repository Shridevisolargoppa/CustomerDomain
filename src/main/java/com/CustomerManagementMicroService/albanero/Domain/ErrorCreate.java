package com.CustomerManagementMicroService.albanero.Domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorCreate {
    private final int status;
    private final String error;


    public ErrorCreate(int status, String error) {

        this.status = status;
        this.error = error;

    }


}
