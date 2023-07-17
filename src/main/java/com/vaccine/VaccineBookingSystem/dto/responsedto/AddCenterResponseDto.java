package com.vaccine.VaccineBookingSystem.dto.responsedto;

import com.vaccine.VaccineBookingSystem.enums.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCenterResponseDto {
    String centerName;
    CenterType centerType;
    String address;
}
