package com.vaccine.VaccineBookingSystem.exception;

import com.vaccine.VaccineBookingSystem.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public class CertificateAlreadyGenerated extends RuntimeException {
    public CertificateAlreadyGenerated(String message) {
        super(message);
    }
}
