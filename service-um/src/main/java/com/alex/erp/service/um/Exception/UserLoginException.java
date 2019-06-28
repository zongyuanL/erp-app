package com.alex.erp.service.um.Exception;

public class UserLoginException extends RuntimeException{
    public UserLoginException(String message) {
        super(message);
    }
}