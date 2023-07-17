package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddDoctorRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddDoctorResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add-doctor")
    public ResponseEntity addDoctor(@RequestBody AddDoctorRequestDto addDoctorRequestDto){
        try {
            AddDoctorResponseDto addDoctorResponseDto = doctorService.addDoctor(addDoctorRequestDto);
            return new ResponseEntity(addDoctorResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-age-greater-than")
    public ResponseEntity getByAgeGreaterThan(@RequestParam("age") int age){
        try {
            List<String> doctors = doctorService.getByAgeGreaterThan(age);
            return new ResponseEntity(doctors, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // get the list of doctors who belong to a particular center
    @GetMapping("/get-all-doctors-at-center")
    public ResponseEntity getAllDoctorsAtCenter(@RequestParam("centerName") String centerName){
        try {
            List<String> doctors = doctorService.getAllDoctorsAtCenter(centerName);
            return new ResponseEntity(doctors, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // get the list of doctors who belong to a particular center id
    @GetMapping("/get-all-doctors-at-center-id")
    public ResponseEntity getAllDoctorsAtCenterById(@RequestParam("centerId") int id){
        try {
            List<String> doctors = doctorService.getAllDoctorByCenterId(id);
            return new ResponseEntity(doctors, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-all-doctor")
    public ResponseEntity getAllPerson(){
        try{
            List<AddDoctorResponseDto> response = doctorService.getAllPerson();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
