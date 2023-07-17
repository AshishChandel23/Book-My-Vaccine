package com.vaccine.VaccineBookingSystem.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateResponse {
    String personName;
    String id;
    String message;
}
