package com.vaccine.VaccineBookingSystem.repository;

import com.vaccine.VaccineBookingSystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "select * from doctor where age > :age",nativeQuery = true)
    List<Doctor> getDoctorGreaterThan(int age);

    @Query(value = "Select * from doctor where center_id= :center_id", nativeQuery=true)
    List<Doctor> getAllDoctorsByCenterId(int center_id);
}
