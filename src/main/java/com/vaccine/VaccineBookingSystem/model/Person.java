package com.vaccine.VaccineBookingSystem.model;

import com.vaccine.VaccineBookingSystem.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int age;
    @Column(unique = true,nullable = false)
    String emailId;

    @Enumerated(value = EnumType.STRING)
    Gender gender;
    boolean dose1Taken;
    boolean dose2Taken;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Dose> dosesTaken = new ArrayList<>();

    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    Certificate certificate;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();
}
