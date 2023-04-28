package com.example.demowithtests.web.passport;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Visa;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.passport.VisaDto;
import com.example.demowithtests.service.passport.PassportService;
import com.example.demowithtests.util.UserIsNotExistException;
import com.example.demowithtests.util.config.passport.PassportMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassportControllerBean implements PassportController {
    private final PassportService passportService;
    private final PassportMapper passportMapper;

    @Override
    public PassportResponseDto savePassport(PassportRequestDto requestDto) {
        log.info("savePassport() started");
        Passport passport = passportMapper.fromRequestDto(requestDto);
        PassportResponseDto passportResponseDto = passportMapper.toResponseDto(passportService.create(passport));
        log.info("savePassport() completed");
        return passportResponseDto;
    }

    @Override
    public List<PassportResponseDto> getAllPassports() {
        log.info("getAllPassports() started");
        List<Passport> passports = passportService.getAll();
        List<PassportResponseDto> responseDtos = new ArrayList<>();
        for (Passport passport : passports) {
            responseDtos.add(passportMapper.toResponseDto(passport));
        }
        log.info("getAllPassports() completed");
        return responseDtos;
    }

    @Override
    public PassportResponseDto getPassportById(String id) {
        log.info("getPassportById() started");
        Passport passport = passportService.getById(id);
        PassportResponseDto passportResponseDto = passportMapper.toResponseDto(passport);
        log.info("getPassportById() completed");
        return passportResponseDto;
    }

    @Override
    public PassportResponseDto refreshPassport(Integer id, PassportRequestDto requestDto) throws UserIsNotExistException {
        log.info("refreshPassport() started");
        PassportResponseDto responseDto = passportMapper
                .toResponseDto(passportService.updateById(id, passportMapper.fromRequestDto(requestDto)));
        log.info("refreshPassport() completed");
        return responseDto;
    }

    @Override
    public PassportResponseDto addVisa(String id, VisaDto visaDto) {
        log.info("passport addVisa() started");
        Visa visa = passportMapper.map(visaDto);
        PassportResponseDto passportResponseDto = passportMapper
                .toResponseDto(passportService.addVisa(id, visa));
        log.info("passport addVisa() completed");
        return passportResponseDto;
    }

    @Override
    public Set<VisaDto> getVisas(String passportId) {
        log.info("passport getVisas() started");
        Set<VisaDto> visasDto = passportMapper.fromVisas(passportService.getVisas(passportId));
        log.info("passport getVisas() completed");
        return visasDto;
    }
}
