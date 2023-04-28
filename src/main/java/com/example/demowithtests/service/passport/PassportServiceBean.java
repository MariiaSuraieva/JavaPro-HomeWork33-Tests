package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Visa;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class PassportServiceBean implements PassportService {

    private final PassportRepository passportRepository;

    @SneakyThrows
    @Override
    public Passport create(Passport passport) {
        log.debug("passport create() started");
        passport.setIsFree(true);
        Optional.of(passport)
                //.filter(p -> p.getFirstName() != null && p.getSecondName() != null)
                .map(passportRepository::save);
                //.orElseThrow(EmptyDataException::new);
        log.debug("passport create() completed, passport with id " + passport.getId() + " created");
        return passport;
    }

    @Override
    public List<Passport> getAll() {
        log.debug("passport getAll() started");
        List<Passport> passports = passportRepository.findAll();
        if(passports.size() == 0) throw new ListHasNoAnyElementsException();
        log.debug("passport getAll() finished, you get " + passports.size() + " passports");
        return passports;
    }

    @Override
    public Passport getById(String id) {
        log.debug("passport getById() - start: id = {}", id);
        try {
            Integer passportId = Integer.parseInt(id);
            Passport passport = passportRepository.findById(passportId)
                    .orElseThrow(IdIsNotExistException::new);
            log.debug("getById() - end: employee = {}", passport);
            return passport;
        } catch (NumberFormatException exception) {
            log.debug("passport getById() - end: passport = {}", exception);
            throw new WrongDataException();
        }
    }

    @Override
    public Passport updateById(Integer id, Passport plane) {
        log.debug("passport updateById() started");
        Passport passportUpdating = passportRepository.findById(id)
                .map(entity -> {
                    entity.setFirstName(plane.getFirstName());
                    entity.setSecondName(plane.getSecondName());
                    return passportRepository.save(entity);
                })
                .orElseThrow(IdIsNotExistException::new);
        log.debug("passport updateById() completed for id = " + id);
        return passportUpdating;
    }

    @Override
    public Passport addVisa(String id, Visa visa) {
        log.debug("passport addVisa() - start: id = {}", id);
        try {
            Integer passportId = Integer.parseInt(id);
            Passport passport = passportRepository.findById(passportId)
                    .orElseThrow(IdIsNotExistException::new);
            Set<Visa> visas = passport.getVisas();
            visas.add(visa);
            passport.setVisas(visas);
            passportRepository.save(passport);
            log.debug("passport addVisa() - end: passport = {}");
            return passport;
        } catch (NumberFormatException exception) {
            throw new WrongDataException();
        }
    }

    @Override
    public Set<Visa> getVisas(String id) {
        log.debug("passport getVisa() - start: id = {}", id);
        try {
            Integer passportId = Integer.parseInt(id);
            Passport passport = passportRepository.findById(passportId)
                    .orElseThrow(IdIsNotExistException::new);
            Set<Visa> visas = passport.getVisas();
            if(visas.size()==0) throw new ListHasNoAnyElementsException();
            log.debug("passport getVisa() - completed");
            return visas;
        } catch (NumberFormatException exception) {
            throw new WrongDataException();
        }
    }
}
