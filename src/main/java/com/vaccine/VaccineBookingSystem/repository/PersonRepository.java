package com.vaccine.VaccineBookingSystem.repository;

import com.vaccine.VaccineBookingSystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmailId(String oldEmail);

    @Query(value = "Select * from person as p where p.gender='MALE' and p.age > :age", nativeQuery=true)
    List<Person> getMalesGreaterThanCertainAge(int age);

    @Query(value = "Select * from person as p where p.dose1taken=true && p.dose2taken=false", nativeQuery=true)
    List<Person> getPersonTakenOnly1Vaccine();
    @Query(value = "Select * from person as p where p.dose1taken=true && p.dose2taken=true", nativeQuery=true)
    List<Person> getFullyVaccinatedperson();
    @Query(value = "Select * from person as p where p.dose1taken=false && p.dose2taken=false", nativeQuery=true)
    List<Person> getPersonNotTakenAnyVaccine();
}
