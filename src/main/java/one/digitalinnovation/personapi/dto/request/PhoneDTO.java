package one.digitalinnovation.personapi.dto.request;

import one.digitalinnovation.personapi.enums.PhoneType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PhoneDTO {

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    @NotEmpty
    @Size(min = 13, max = 15)
    private String number;
}