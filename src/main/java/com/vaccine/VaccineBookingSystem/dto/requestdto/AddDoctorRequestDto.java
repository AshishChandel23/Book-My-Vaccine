package com.vaccine.VaccineBookingSystem.dto.requestdto;

import com.vaccine.VaccineBookingSystem.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddDoctorRequestDto {
    int centerId;
    String name;
    int age;
    String email;
    Gender gender;
}
