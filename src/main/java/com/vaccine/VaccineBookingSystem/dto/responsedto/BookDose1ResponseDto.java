package com.vaccine.VaccineBookingSystem.dto.responsedto;

import com.vaccine.VaccineBookingSystem.enums.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDose1ResponseDto {

    DoseType doseType;
    Date vaccinationDate;
    int noOfDoseTaken;

    AddPersonResponseDto addPersonResponseDto;
}
