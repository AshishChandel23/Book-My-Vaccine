package com.vaccine.VaccineBookingSystem.service;

import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.CertificateResponse;
import com.vaccine.VaccineBookingSystem.exception.CertificateAlreadyGenerated;
import com.vaccine.VaccineBookingSystem.exception.Dose1NotTaken;
import com.vaccine.VaccineBookingSystem.exception.PersonNotFoundException;
import com.vaccine.VaccineBookingSystem.model.Certificate;
import com.vaccine.VaccineBookingSystem.model.Person;
import com.vaccine.VaccineBookingSystem.repository.CertificateRepository;
import com.vaccine.VaccineBookingSystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private PersonRepository personRepository;
    public CertificateResponse getCertficate(int personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Person Id is Invalid");
        }
        Person person = optionalPerson.get();
        if(!person.isDose1Taken()){
            throw new Dose1NotTaken("Person doesn't taken Dose 1 yet...!");
        }
        if(!person.isDose2Taken()){
            throw new Dose1NotTaken("Person doesn't taken Dose 2 yet...!");
        }
        if(person.getCertificate()!=null){
            throw new CertificateAlreadyGenerated("Certificate Already generated for that person Id");
        }

        Certificate certificate = new Certificate();
        certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
        certificate.setConfirmationMessage("Certificate generated successfully");
        certificate.setPerson(person);

        person.setCertificate(certificate);
        Person savedPerson = personRepository.save(person);

        CertificateResponse certificateResponse = new CertificateResponse();
        certificateResponse.setPersonName(savedPerson.getName());
        certificateResponse.setId(certificate.getCertificateNo());
        certificateResponse.setMessage("Congrats! you taken both vaccine and Certificate will generated and send to you email Id...!");
        return certificateResponse;
    }

    public List<CertificateResponse> getAllCertficate() {
        List<CertificateResponse> ans = new ArrayList<>();
        List<Certificate> certificates = certificateRepository.findAll();
        for(Certificate c : certificates){
            CertificateResponse certificateResponse = new CertificateResponse();
            certificateResponse.setPersonName(c.getPerson().getName());
            certificateResponse.setId(c.getCertificateNo());
            certificateResponse.setMessage("Generated successfully");
            ans.add(certificateResponse);
        }
        return ans;
    }
}
