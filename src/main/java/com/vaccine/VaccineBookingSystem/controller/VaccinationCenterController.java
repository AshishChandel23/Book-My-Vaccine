package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddCenterRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddCenterResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.service.VaccinationCenterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add-center")
    public ResponseEntity addCenter(@RequestBody AddCenterRequestDto addCenterRequestDto){
        AddCenterResponseDto addCenterResponseDto = vaccinationCenterService.addCenter(addCenterRequestDto);
        return new ResponseEntity(addCenterResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-vaccination-center")
    public ResponseEntity getAllVaccinationCenter(){
        try{
            List<AddCenterResponseDto> response = vaccinationCenterService.getAllVaccinationCenter();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
