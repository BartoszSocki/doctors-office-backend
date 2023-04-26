package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class DoctorPublicInfoDto extends RepresentationModel<DoctorPublicInfoDto> {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String specialization;

}
