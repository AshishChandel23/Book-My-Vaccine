package com.vaccine.VaccineBookingSystem.service;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddDoctorRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddCenterResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddDoctorResponseDto;
import com.vaccine.VaccineBookingSystem.exception.CenterNotFoundException;
import com.vaccine.VaccineBookingSystem.model.Doctor;
import com.vaccine.VaccineBookingSystem.model.VaccinationCenter;
import com.vaccine.VaccineBookingSystem.repository.DoctorRepository;
import com.vaccine.VaccineBookingSystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public AddDoctorResponseDto addDoctor(AddDoctorRequestDto addDoctorRequestDto) {

        //prepare dto -> entity
        Optional<VaccinationCenter> responseCenter = vaccinationCenterRepository.findById(addDoctorRequestDto.getCenterId());

        VaccinationCenter center = responseCenter.get();
        if(responseCenter.isEmpty()){
            throw new CenterNotFoundException("Center doesn't found");
        }
        // create doctor entity
        Doctor doctor = new Doctor();
        doctor.setName(addDoctorRequestDto.getName());
        doctor.setAge(addDoctorRequestDto.getAge());
        doctor.setEmail(addDoctorRequestDto.getEmail());
        doctor.setGender(addDoctorRequestDto.getGender());
        doctor.setCenter(center);
        // add in center's doctor list
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = vaccinationCenterRepository.save(center);

        //prepare entity -> dto

        AddDoctorResponseDto addDoctorResponseDto = new AddDoctorResponseDto();

        List<Doctor> doctors = savedCenter.getDoctors();
        Doctor latestSavedDoctor  = doctors.get(doctors.size()-1);

        AddCenterResponseDto addCenterResponseDto = new AddCenterResponseDto();
        addCenterResponseDto.setCenterName(savedCenter.getCenterName());
        addCenterResponseDto.setCenterType(savedCenter.getCenterType());
        addCenterResponseDto.setAddress(savedCenter.getAddress());

        addDoctorResponseDto.setName(latestSavedDoctor.getName());
        addDoctorResponseDto.setMessage("Doctor Successfully registered in the center");
        addDoctorResponseDto.setAddCenterResponseDto(addCenterResponseDto);

        return addDoctorResponseDto;

    }

    public List<String> getByAgeGreaterThan(int age) {

        List<Doctor> doctors = doctorRepository.getDoctorGreaterThan(age);
        List<String> list = new ArrayList<>();

        for(Doctor d : doctors) list.add(d.getName());

        return list;
    }

    public List<String> getAllDoctorsAtCenter(String centerName) {

        List<String> doctors = vaccinationCenterRepository.getAllDoctorsAtCenter(centerName);

        return doctors;
    }
    public List<String> getAllDoctorByCenterId(int id) {

        List<Doctor> doctors = doctorRepository.getAllDoctorsByCenterId(id);
        List<String> list = new ArrayList<>();

        for(Doctor d : doctors) list.add(d.getName());

        return list;
    }

    public List<AddDoctorResponseDto> getAllPerson() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<AddDoctorResponseDto> ans = new ArrayList<>();
        for(Doctor d : doctors){
            AddDoctorResponseDto addDoctorResponseDto = new AddDoctorResponseDto();
            addDoctorResponseDto.setName(d.getName());
            addDoctorResponseDto.setMessage("Doctor Id : "+d.getId()+" Doctor Email Id : "+d.getEmail());
            AddCenterResponseDto addCenterResponseDto = new AddCenterResponseDto();
            VaccinationCenter center = d.getCenter();
            addCenterResponseDto.setCenterName(center.getCenterName());
            addCenterResponseDto.setCenterType(center.getCenterType());
            addCenterResponseDto.setAddress(center.getAddress());
            addDoctorResponseDto.setAddCenterResponseDto(addCenterResponseDto);
            ans.add(addDoctorResponseDto);
        }
        return ans;
    }
}
