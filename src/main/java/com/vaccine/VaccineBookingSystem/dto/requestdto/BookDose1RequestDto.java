package com.vaccine.VaccineBookingSystem.dto.requestdto;

import com.vaccine.VaccineBookingSystem.enums.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDose1RequestDto {
    int id;
    DoseType doseType;
}
