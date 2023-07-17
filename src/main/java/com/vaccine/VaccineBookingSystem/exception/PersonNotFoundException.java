package com.vaccine.VaccineBookingSystem.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
