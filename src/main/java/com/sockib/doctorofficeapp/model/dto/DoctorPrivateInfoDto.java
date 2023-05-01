package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.entities.RegisteredUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class DoctorPrivateInfoDto extends RepresentationModel<DoctorPrivateInfoDto> {

    private String specialization;
    private RegisteredUser registeredUser;

}
