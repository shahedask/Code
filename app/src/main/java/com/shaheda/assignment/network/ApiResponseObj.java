package com.shaheda.assignment.network;

public class ApiResponseObj {

    private String status;
    private String message;

    public ApiResponseObj(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
