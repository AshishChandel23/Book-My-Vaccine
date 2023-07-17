package com.vaccine.VaccineBookingSystem.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookAppointmentResponseDto {

    String personName;
    String doctorName;
    String appointmentId;
    Date appointmentDate;

    AddCenterResponseDto addCenterResponseDto;
}
