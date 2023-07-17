package com.vaccine.VaccineBookingSystem.dto.requestdto;

import com.vaccine.VaccineBookingSystem.enums.Gender;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPersonRequestDto {
    String name;
    int age;
    String email;
    Gender gender;
}
