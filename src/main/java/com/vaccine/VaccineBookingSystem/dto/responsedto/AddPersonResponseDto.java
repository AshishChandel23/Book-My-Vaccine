package com.vaccine.VaccineBookingSystem.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPersonResponseDto {
    String name;
    String message;
}
