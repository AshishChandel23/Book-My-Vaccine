package com.vaccine.VaccineBookingSystem.model;

import com.vaccine.VaccineBookingSystem.enums.CenterType;
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
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String centerName;

    @Enumerated(value = EnumType.STRING)
    CenterType centerType;

    String address;

    @OneToMany( mappedBy = "center", cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();
}
