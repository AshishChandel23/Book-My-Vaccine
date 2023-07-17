package com.vaccine.VaccineBookingSystem.repository;

import com.vaccine.VaccineBookingSystem.model.Doctor;
import com.vaccine.VaccineBookingSystem.model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {

    @Query(value = "Select concat(d.name,' ',d.gender,' ',d.age,' ',c.center_name,' ',c.center_type,' ',c.address) as Details " +
            "from doctor as d inner join vaccination_center as c on  c.id=d.center_id " +
            "where c.center_name= :centerName", nativeQuery=true)
//@Query(value = "Select d.id,d.name,d.age,d.email,d.gender,d.center_id from doctor as d inner join vaccination_center as c on  c.id=d.center_id " +
//        "where c.center_name= :centerName", nativeQuery=true)
    List<String> getAllDoctorsAtCenter(String centerName);


}
