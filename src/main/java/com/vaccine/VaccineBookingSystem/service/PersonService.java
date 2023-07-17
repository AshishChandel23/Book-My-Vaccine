package com.vaccine.VaccineBookingSystem.service;

import com.vaccine.VaccineBookingSystem.dto.requestdto.AddPersonRequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.exception.EamilIdNotFoundException;
import com.vaccine.VaccineBookingSystem.model.Person;
import com.vaccine.VaccineBookingSystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        // Convert Request Dto -> Enttity
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmailId(addPersonRequestDto.getEmail());
        person.setGender(addPersonRequestDto.getGender());
//        person.setDose1Taken(false);
//        person.setDose2Taken(false);
//        person.setCertificate(null);

        Person savedPerson = personRepository.save(person);

        // saved entity -> response dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Person Successfully registered....!");
        return addPersonResponseDto;
    }

    public String updateEmail(String oldEmail, String newEmail) {
        Optional<Person> optionalPerson = personRepository.findByEmailId(oldEmail);
        if(optionalPerson.isEmpty()){
            throw new EamilIdNotFoundException("Invalid Email id : Check your email id");
        }
        optionalPerson.get().setEmailId(newEmail);
        personRepository.save(optionalPerson.get());
        return "Email Id successfully Updated";
    }

    public List<String> getAllMalesGreaterThanCertainAge(int age) {
        List<Person> personList = personRepository.getMalesGreaterThanCertainAge(age);
        List<String> list = new ArrayList<>();

        for(Person p : personList) list.add(p.getName());

        return list;
    }

    public List<String> getAllPersonTakenBothVaccine() {
        List<Person> personList = personRepository.getFullyVaccinatedperson();
        List<String> list = new ArrayList<>();

        for(Person p : personList) list.add(p.getName());

        return list;
    }

    public List<String> getAllPersonNotTakenAnyVaccine() {
        List<Person> personList = personRepository.getPersonNotTakenAnyVaccine();
        List<String> list = new ArrayList<>();

        for(Person p : personList) list.add(p.getName());

        return list;
    }

    public List<String> getAllPersonTakenOnly1Vaccine() {
        List<Person> personList = personRepository.getPersonTakenOnly1Vaccine();
        List<String> list = new ArrayList<>();

        for(Person p : personList) list.add(p.getName());

        return list;
    }

    public List<AddPersonResponseDto> getAllPerson() {
        List<Person> persons = personRepository.findAll();
        List<AddPersonResponseDto> ans = new ArrayList<>();
        for(Person p : persons){
            AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Person Id : "+p.getId()+" Person Email : "+p.getEmailId());
            ans.add(addPersonResponseDto);
        }
        return ans;
    }
}
