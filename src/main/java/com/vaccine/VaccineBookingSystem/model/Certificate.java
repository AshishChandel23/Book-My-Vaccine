package com.vaccine.VaccineBookingSystem.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String certificateNo;
    String confirmationMessage;

    @OneToOne
    @JoinColumn
    Person person;
}
