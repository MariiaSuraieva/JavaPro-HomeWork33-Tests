package com.example.demowithtests.util.config.passport;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Visa;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.passport.VisaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    Passport fromRequestDto(PassportRequestDto requestDto);

    PassportRequestDto toRequestDto(Passport passport);
    @Mapping(target = "firstName", source = "passport.firstName")
    @Mapping(target = "secondName", source = "passport.secondName")
    @Mapping(target = "visasDto", source = "passport.visas")
    PassportResponseDto toResponseDto(Passport passport);

    Passport fromResponseDto(PassportResponseDto responseDto);
    Set<VisaDto> fromVisas(Set<Visa> visas);
    Set<Visa> fromVisasDto(Set<VisaDto> visasDto);
    Visa map(VisaDto visaDto);
    VisaDto map(Visa visa);


}
