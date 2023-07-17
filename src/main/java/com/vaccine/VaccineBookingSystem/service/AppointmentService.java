package com.vaccine.VaccineBookingSystem.service;

import com.vaccine.VaccineBookingSystem.dto.requestdto.BookAppointmentRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddCenterResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.BookAppointmentResponseDto;
import com.vaccine.VaccineBookingSystem.exception.DoctorNotFoundException;
import com.vaccine.VaccineBookingSystem.exception.PersonNotFoundException;
import com.vaccine.VaccineBookingSystem.model.Appointment;
import com.vaccine.VaccineBookingSystem.model.Doctor;
import com.vaccine.VaccineBookingSystem.model.Person;
import com.vaccine.VaccineBookingSystem.model.VaccinationCenter;
import com.vaccine.VaccineBookingSystem.repository.AppointmentRepository;
import com.vaccine.VaccineBookingSystem.repository.DoctorRepository;
import com.vaccine.VaccineBookingSystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    private void sendMail(String to,String personName,String doctorName,String centerName,String centerAddress, String date){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String text = "Congrats!! "+personName+" Your appointment has been booked with Doctor "+
                doctorName + ". Your vaccination center name is: " + centerName + " Please reach at this address "+
                centerAddress + " at this time: " + date+" Dhanyawad!!!";
        simpleMailMessage.setSubject("Congrats!! Appointment Done");
        simpleMailMessage.setFrom("acciojobspring@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {

        Optional<Person> personOptional = personRepository.findById(bookAppointmentRequestDto.getPersonId());

        if(personOptional.isEmpty()) throw new PersonNotFoundException("Person Id is Invalid");

        Optional<Doctor> doctorOptional = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());

        if(doctorOptional.isEmpty()) throw new DoctorNotFoundException("Doctor Id is Invalid");

        Person person = personOptional.get();
        Doctor doctor = doctorOptional.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));

        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        person.getAppointments().add(appointment);
        doctor.getAppointments().add(savedAppointment);

        Doctor savedDoctor = doctorRepository.save(doctor);
        Person savedPerson = personRepository.save(person);

        VaccinationCenter center = savedDoctor.getCenter();

        //send Mail
        sendMail(savedPerson.getEmailId(),
                savedPerson.getName(),
                savedDoctor.getName(),
                center.getCenterName(),
                center.getAddress(),
                savedAppointment.getAppointmentDate().toString());

        AddCenterResponseDto addCenterResponseDto = new AddCenterResponseDto();
        addCenterResponseDto.setCenterName(center.getCenterName());
        addCenterResponseDto.setCenterType(center.getCenterType());
        addCenterResponseDto.setAddress(center.getAddress());


        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(savedAppointment.getId());
        bookAppointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());
        bookAppointmentResponseDto.setAddCenterResponseDto(addCenterResponseDto);

        return bookAppointmentResponseDto;
    }

    public List<Object> getAllAppointmentsOfDoctor(int id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        if(doctorOptional.isEmpty()) throw new DoctorNotFoundException("Doctor Id is Invalid");

        Doctor doctor =  doctorOptional.get();
        List<Appointment> appointmentList = appointmentRepository.findBYDoctorId(id);
        List<Object> ans = new ArrayList<>();
        for(Appointment app : appointmentList){
            Custom cs = new Custom();
            cs.setDoctorName(doctor.getName());
            cs.setAppointmentId(app.getAppointmentId());
            cs.setAppointmentDateAndTime(app.getAppointmentDate().toString());
            ans.add(cs);
        }
        return ans;
    }
    public List<Object> getAllAppointmentsOfPerson(int id) {
        Optional<Person> personOptional = personRepository.findById(id);

        if(personOptional.isEmpty()) throw new PersonNotFoundException("Person Id is Invalid");

        Person person = personOptional.get();
        List<Appointment> appointmentList = appointmentRepository.findBYPersonId(id);
        List<Object> ans = new ArrayList<>();
        for(Appointment app : appointmentList){
//            Object obj = new Object(){
//                String doctorName = person.getName();
//                String appointmentDateAndTime = app.getAppointmentDate().toString();
//            };
//            ans.add(obj);
            CustomPerson cs = new CustomPerson();
            cs.setPersonName(person.getName());
            cs.setAppointmentId(app.getAppointmentId());
            cs.setAppointmentDateAndTime(app.getAppointmentDate().toString());
            ans.add(cs);
        }
        return ans;
    }
}
class CustomPerson{
    private String personName;

    private String appointmentId;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    private String appointmentDateAndTime;


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String doctorName) {
        this.personName = doctorName;
    }

    public String getAppointmentDateAndTime() {
        return appointmentDateAndTime;
    }

    public void setAppointmentDateAndTime(String appointmentDateAndTime) {
        this.appointmentDateAndTime = appointmentDateAndTime;
    }
}
class Custom{
    private String doctorName;
    private String appointmentId;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    private String appointmentDateAndTime;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDateAndTime() {
        return appointmentDateAndTime;
    }

    public void setAppointmentDateAndTime(String appointmentDateAndTime) {
        this.appointmentDateAndTime = appointmentDateAndTime;
    }
}

