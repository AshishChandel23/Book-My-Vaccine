package com.vaccine.VaccineBookingSystem.controller;

import com.vaccine.VaccineBookingSystem.dto.requestdto.BookAppointmentRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.BookAppointmentResponseDto;
import com.vaccine.VaccineBookingSystem.model.Appointment;
import com.vaccine.VaccineBookingSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){
        try {
            BookAppointmentResponseDto bookAppointmentResponseDto = appointmentService.bookAppointment(bookAppointmentRequestDto);
            return new ResponseEntity(bookAppointmentResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // get all the appointments of a particular doctor
    @GetMapping("/get-all-appointments-of-doctor")
    public ResponseEntity getAllAppointmentsOfDoctor(@RequestParam("doctorId") int id){
        try{
            List<Object> response = appointmentService.getAllAppointmentsOfDoctor(id);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // get all the appointments for a particular person
    @GetMapping("/get-all-appointments-of-person")
    public ResponseEntity getAllAppointmentsOfPerson(@RequestParam("personId") int id){
        try{
            List<Object> response = appointmentService.getAllAppointmentsOfPerson(id);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-appointments")
    public ResponseEntity getAllAppointments(){
        try{
            List<BookAppointmentResponseDto> response = appointmentService.getAllAppointments();
            return new ResponseEntity(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
