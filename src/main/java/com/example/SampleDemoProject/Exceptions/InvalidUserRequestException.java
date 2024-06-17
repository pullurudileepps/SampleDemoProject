package com.example.SampleDemoProject.Exceptions;

public class InvalidUserRequestException extends  RuntimeException {
    public InvalidUserRequestException(String message) {
        super(message);
    }
}
