package com.sockib.doctorofficeapp.config;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.dto.ClientPrivateInfoDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public TypeMap<PlannedVisit, PlannedVisitDto> plannedVisitToPlannedVisitDtoTypeMap(ModelMapper modelMapper) {
//        TypeMap<PlannedVisit, PlannedVisitDto> plannedVisitMapper =
//                modelMapper.createTypeMap(PlannedVisit.class, PlannedVisitDto.class);
//
//        plannedVisitMapper.addMappings(mapper ->mapper.map(src ->
//                src.getRegisteredUser().getId(),PlannedVisitDto::setRegisteredClientId));
//        plannedVisitMapper.addMappings(mapper ->mapper.map(src ->
//                src.getRegisteredDoctor().getId(),PlannedVisitDto::setRegisteredDoctorId));
//        plannedVisitMapper.addMappings(mapper ->mapper.map(src ->
//                src.getScheduledVisit().getId(),PlannedVisitDto::setScheduledVisitId));
//
//        return plannedVisitMapper;
//    }

    @Bean
    public TypeMap<ClientInfo, ClientPrivateInfoDto> clientInfoClientPrivateInfoDtoTypeMap(ModelMapper modelMapper) {
        TypeMap<ClientInfo, ClientPrivateInfoDto> infoMapper =
                modelMapper.createTypeMap(ClientInfo.class, ClientPrivateInfoDto.class);

        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getUsername(), ClientPrivateInfoDto::setUsername));
        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getPassword(), ClientPrivateInfoDto::setPassword));
        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getName(), ClientPrivateInfoDto::setName));
        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getSurname(), ClientPrivateInfoDto::setSurname));
        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getGender(), ClientPrivateInfoDto::setGender));
        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getMobile(), ClientPrivateInfoDto::setMobile));
        infoMapper.addMappings(mapper -> mapper.map(src ->
                src.getRegisteredUser().getVerified(), ClientPrivateInfoDto::setVerified));

        return infoMapper;
    }

    @Bean
    public TypeMap<ScheduledVisit, ScheduledVisitDto> scheduledVisitToScheduledVisitDtoTypeMap(ModelMapper modelMapper) {
        TypeMap<ScheduledVisit, ScheduledVisitDto> scheduledVisitMapper =
                modelMapper.createTypeMap(ScheduledVisit.class, ScheduledVisitDto.class);
        scheduledVisitMapper.addMappings(mapper ->
                mapper.map(src -> src.getRegisteredDoctor().getId(), ScheduledVisitDto::setRegisteredDoctorId));

        return scheduledVisitMapper;
    }

}
