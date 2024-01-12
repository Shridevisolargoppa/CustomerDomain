package com.CustomerManagementMicroService.albanero.Domain;

public class ErrorCreate {
    private final int status;
    private final String error;


    public ErrorCreate(int status, String error) {

        this.status = status;
        this.error = error;

    }


}
