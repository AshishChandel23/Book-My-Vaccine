package com.vaccine.VaccineBookingSystem.exception;


public class CenterNotFoundException extends RuntimeException{

    public CenterNotFoundException(String message){
        super(message);
    }
}
