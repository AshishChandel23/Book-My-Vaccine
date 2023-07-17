package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddCenterRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddCenterResponseDto;
import com.vaccine.VaccineBookingSystem.service.VaccinationCenterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
