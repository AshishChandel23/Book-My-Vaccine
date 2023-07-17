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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int age;

    @Column(unique = true,nullable = false)
    String email;

    @Enumerated(value=EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    VaccinationCenter center;
}
