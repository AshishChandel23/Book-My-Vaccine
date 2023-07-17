package com.vaccine.VaccineBookingSystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String appointmentId;

    @CreationTimestamp
    Date appointmentDate;

    @ManyToOne
    @JoinColumn
    Person person;

    @ManyToOne
    @JoinColumn
    Doctor doctor;
}
