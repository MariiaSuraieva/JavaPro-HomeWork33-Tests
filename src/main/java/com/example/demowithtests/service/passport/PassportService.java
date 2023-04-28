package com.example.demowithtests.service.passport;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Visa;
import com.example.demowithtests.util.UserIsNotExistException;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PassportService {
    @SneakyThrows
    Passport create(Passport passport);

    List<Passport> getAll();

    Passport getById(String id);

    Passport updateById(Integer id, Passport plane) throws UserIsNotExistException;
    Passport addVisa(String idPassport, Visa visa);
    Set<Visa> getVisas(String idPassport);

}
