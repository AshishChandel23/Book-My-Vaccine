package com.vaccine.VaccineBookingSystem.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddDoctorResponseDto {
    String name;
    String message;
    AddCenterResponseDto addCenterResponseDto;
}
