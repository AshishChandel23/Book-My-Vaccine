package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.requestdto.BookDose1RequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.BookDose1ResponseDto;
import com.vaccine.VaccineBookingSystem.model.Dose;
import com.vaccine.VaccineBookingSystem.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    private DoseService doseService;

    // Before -----BookDose1Dto-----
//    @PostMapping("/get_dose_1")
//    public ResponseEntity getDose1(@RequestParam("id") int personId, @RequestParam("doseType") DoseType doseType){
//
//        try{
//            Dose doseTake = doseService.getDose1(personId, doseType);
//            return new ResponseEntity(doseTake, HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
    @PostMapping("/get-dose-1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1Dto){

        try{
            BookDose1ResponseDto doseTake = doseService.getDose1(bookDose1Dto);
            return new ResponseEntity(doseTake, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/get-dose-2")
    public ResponseEntity getDose2(@RequestBody BookDose1RequestDto bookDose1Dto){

        try{
            BookDose1ResponseDto doseTake = doseService.getDose2(bookDose1Dto);
            return new ResponseEntity(doseTake, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
