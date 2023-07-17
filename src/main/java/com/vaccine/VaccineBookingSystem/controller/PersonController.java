package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddPersonRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.model.Person;
import com.vaccine.VaccineBookingSystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    //Before -----AddPersonRequestDto-----
//    @PostMapping("/add-person")
//    public ResponseEntity addPerson(@RequestBody Person person){
//        try {
//            Person responsePerson = personService.addPerson(person);
//            return new ResponseEntity(responsePerson, HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    //After ---AddPersonRequestDto----
    @PostMapping("/add-person")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try {
            AddPersonResponseDto responsePerson = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(responsePerson, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // update email of person using old email
    @PutMapping("/update-email")
    public ResponseEntity updateEmail(@RequestParam String oldEmail, @RequestParam String newEmail){
        try{
            String response = personService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // get all males of age greater than a certain age
    @GetMapping("/get-all-males-person-age")
    public ResponseEntity getAllMalesGreaterThanCertainAge(@RequestParam("age") int age){
        try{
            List<String> response = personService.getAllMalesGreaterThanCertainAge(age);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // get all the people who are taken only 1 vaccine
    @GetMapping("/get-person-taken-1-vaccine")
    public ResponseEntity getAllPersonTakenOnly1Vaccine(){
        try{
            List<String> response = personService.getAllPersonTakenOnly1Vaccine();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // get all the people who are fully vaccinated
    @GetMapping("/get-person-taken-both-vaccine")
    public ResponseEntity getAllPersonTakenBothVaccine(){
        try{
            List<String> response = personService.getAllPersonTakenBothVaccine();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all the people who have not taken even a single dose
    @GetMapping("/get-person-not-taken-any-vaccine")
    public ResponseEntity getAllPersonNotTakenAnyVaccine(){
        try{
            List<String> response = personService.getAllPersonNotTakenAnyVaccine();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-person")
    public ResponseEntity getAllPerson(){
        try{
            List<AddPersonResponseDto> response = personService.getAllPerson();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
