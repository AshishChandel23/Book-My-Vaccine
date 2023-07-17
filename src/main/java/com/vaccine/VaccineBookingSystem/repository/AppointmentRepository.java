package com.vaccine.VaccineBookingSystem.repository;

import com.vaccine.VaccineBookingSystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "Select * from appointment where doctor_id= :id", nativeQuery = true)
    List<Appointment> findBYDoctorId(int id);

    @Query(value = "Select * from appointment where person_id= :id", nativeQuery = true)
    List<Appointment> findBYPersonId(int id);
}
