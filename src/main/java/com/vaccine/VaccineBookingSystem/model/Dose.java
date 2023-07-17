package com.vaccine.VaccineBookingSystem.model;

import com.vaccine.VaccineBookingSystem.enums.DoseType;
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
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String doseId;
    @Enumerated(value = EnumType.STRING)
    DoseType doseType;

    @CreationTimestamp
    Date vaccinationDate;

    @ManyToOne
    @JoinColumn
    Person person;
}
