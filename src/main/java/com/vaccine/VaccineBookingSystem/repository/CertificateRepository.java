package com.vaccine.VaccineBookingSystem.repository;

import com.vaccine.VaccineBookingSystem.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

}
