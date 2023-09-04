package com.example.demo.exceptions;

public class AlreadyExistsException extends Exception{

    public AlreadyExistsException(String message){
        super (message);
    }
}
