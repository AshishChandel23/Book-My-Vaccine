package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.responsedto.AddDoctorResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.CertificateResponse;
import com.vaccine.VaccineBookingSystem.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/get-certificate")
    public ResponseEntity getCertificate(@RequestParam("personId") int id){
        try {
            CertificateResponse certificateResponse = certificateService.getCertficate(id);
            return new ResponseEntity(certificateResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-all-certificate")
    public ResponseEntity getAllCertificate(){
        try {
            List<CertificateResponse> certificateResponse = certificateService.getAllCertficate();
            return new ResponseEntity(certificateResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
