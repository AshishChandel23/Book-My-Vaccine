package com.vaccine.VaccineBookingSystem.service;

import com.vaccine.VaccineBookingSystem.dto.requestdto.BookDose1RequestDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.AddPersonResponseDto;
import com.vaccine.VaccineBookingSystem.dto.responsedto.BookDose1ResponseDto;
import com.vaccine.VaccineBookingSystem.exception.DoseAlreadyTakenException;
import com.vaccine.VaccineBookingSystem.exception.PersonNotFoundException;
import com.vaccine.VaccineBookingSystem.model.Dose;
import com.vaccine.VaccineBookingSystem.model.Person;
import com.vaccine.VaccineBookingSystem.repository.DoseRepository;
import com.vaccine.VaccineBookingSystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DoseRepository doseRepository;

//    public Dose getDose1(int personId, DoseType doseType) {
//        Optional<Person> optionalPerson = personRepository.findById(personId);
//
//        // check if person exists or not
//        if(!optionalPerson.isPresent()){
//            throw new PersonNotFoundException("Invalid PersonId");
//        }
//
//        Person person = optionalPerson.get();
//        // check if dose 1 is already taken
//        if(person.isDose1Taken()){
//            throw new DoseAlreadyTakenException("Dose 1 already taken");
//        }
//
//        // Create a Dose
//        Dose dose = new Dose();
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        dose.setPerson(person);
//
//        person.setDose1Taken(true);
//        person.getDosesTaken().add(dose);
//        Person savedPerson = personRepository.save(person);
//
//        return savedPerson.getDosesTaken().get(0);
//    }
    public BookDose1ResponseDto getDose1(BookDose1RequestDto bookDose1Dto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDose1Dto.getId());

        // check if person exists or not
        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Invalid PersonId");
        }

        Person person = optionalPerson.get();
        // check if dose 1 is already taken
        if(person.isDose1Taken()){
            throw new DoseAlreadyTakenException("Dose 1 already taken");
        }

        // Create a Dose
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1Dto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        Dose dose1 =  savedPerson.getDosesTaken().get(0);

        //prepare entity -> dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Person get the dose 1 Successfully");

        BookDose1ResponseDto bookDose1ResponseDto = new BookDose1ResponseDto();
        bookDose1ResponseDto.setDoseType(dose1.getDoseType());
        bookDose1ResponseDto.setVaccinationDate(dose1.getVaccinationDate());
        bookDose1ResponseDto.setNoOfDoseTaken(1);
        bookDose1ResponseDto.setAddPersonResponseDto(addPersonResponseDto);

        return bookDose1ResponseDto;
    }
    public BookDose1ResponseDto getDose2(BookDose1RequestDto bookDose1Dto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDose1Dto.getId());

        // check if person exists or not
        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Invalid PersonId");
        }

        Person person = optionalPerson.get();
        // check if dose 1 is taken or not if not throw exception
        if(!person.isDose1Taken()){
            throw new DoseAlreadyTakenException("Please firstly take Dose 1");
        }

        // Create a Dose
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1Dto.getDoseType());
        dose.setPerson(person);

        person.setDose2Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        Dose dose2 =  savedPerson.getDosesTaken().get(1);

        //prepare entity -> dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Person get the dose 2 Successfully");

        BookDose1ResponseDto bookDose1ResponseDto = new BookDose1ResponseDto();
        bookDose1ResponseDto.setDoseType(dose2.getDoseType());
        bookDose1ResponseDto.setVaccinationDate(dose2.getVaccinationDate());
        bookDose1ResponseDto.setNoOfDoseTaken(2);
        bookDose1ResponseDto.setAddPersonResponseDto(addPersonResponseDto);

        return bookDose1ResponseDto;
    }
}
