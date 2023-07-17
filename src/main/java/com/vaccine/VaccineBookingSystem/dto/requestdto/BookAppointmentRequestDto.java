package com.vaccine.VaccineBookingSystem.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookAppointmentRequestDto {
    int personId;
    int doctorId;
}
