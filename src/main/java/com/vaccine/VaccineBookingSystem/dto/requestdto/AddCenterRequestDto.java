package com.vaccine.VaccineBookingSystem.dto.requestdto;

import com.vaccine.VaccineBookingSystem.enums.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCenterRequestDto {
    String centerName;
    CenterType centerType;
    String address;
}
