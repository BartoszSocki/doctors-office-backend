package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.DoctorPrivateInfoDto;
import com.sockib.doctorofficeapp.model.dto.DoctorPublicInfoDto;
import com.sockib.doctorofficeapp.services.DoctorInfoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorInfoController {

    private ModelMapper modelMapper;
    private DoctorInfoService doctorInfoService;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/info")
    public ResponseEntity<DoctorPrivateInfoDto> privateDoctorInfo(Principal principal) {
        var doctorInfo = doctorInfoService.getDoctorInfo(principal.getName());
        var doctorInfoDto = modelMapper.map(doctorInfo, DoctorPrivateInfoDto.class);

        doctorInfoDto.add(linkTo(methodOn(DoctorInfoController.class).privateDoctorInfo(principal)).withSelfRel());
        doctorInfoDto.add(linkTo(methodOn(DoctorInfoController.class).publicDoctorInfo(doctorInfo.getId())).withRel("publicDoctorInfo"));

        return ResponseEntity.ok(doctorInfoDto);
    }

    @GetMapping(path = "/{doctorId}/info")
    public ResponseEntity<DoctorPublicInfoDto> publicDoctorInfo(@PathVariable Long doctorId) {
        var doctorInfo = doctorInfoService.getDoctorInfo(doctorId);
        var doctorInfoDto = modelMapper.map(doctorInfo, DoctorPublicInfoDto.class);

        doctorInfoDto.add(linkTo(methodOn(DoctorInfoController.class).publicDoctorInfo(doctorId)).withSelfRel());

        return ResponseEntity.ok(doctorInfoDto);
    }
}
