package com.vaccine.VaccineBookingSystem.service;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddCenterRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddCenterResponseDto;
import com.vaccine.VaccineBookingSystem.model.VaccinationCenter;
import com.vaccine.VaccineBookingSystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public AddCenterResponseDto addCenter(AddCenterRequestDto addCenterRequestDto) {

        VaccinationCenter center = new VaccinationCenter();

        center.setCenterName(addCenterRequestDto.getCenterName());
        center.setCenterType(addCenterRequestDto.getCenterType());
        center.setAddress(addCenterRequestDto.getAddress());

        VaccinationCenter savedCenter = vaccinationCenterRepository.save(center);

        AddCenterResponseDto addCenterResponseDto = new AddCenterResponseDto();
        addCenterResponseDto.setCenterName(savedCenter.getCenterName());
        addCenterResponseDto.setCenterType(savedCenter.getCenterType());
        addCenterResponseDto.setAddress(savedCenter.getAddress());

        return addCenterResponseDto;
    }
}
