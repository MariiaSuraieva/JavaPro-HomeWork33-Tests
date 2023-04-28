package com.example.demowithtests.service.employee;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Workspace;
import com.example.demowithtests.util.UserIsNotExistException;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(String id);

    Employee updateById(Integer id, Employee plane) throws UserIsNotExistException;

    void removeById(Integer id);

    void removeAll();

    List<Employee> sendEmailByCountry(String country, String text);

    List<Employee> sendEmailByCity(String city, String text);

    Employee createrEmployee(String name, String country, String email);

    void fillingDataBase(String quantity);

    void updaterByCountryFully(String countries);

    void updaterByCountrySmart(String countries);

    String randomCountry(String countriesString);

    List<Employee> processor();

    void emailSenderPhotoChange();

    void emailSenderWhoMovedFromUkraine(String country);

    Employee addPassport(Integer employeeId, Integer passportId);
    Employee addPassport(Integer employeeId);

    Employee connectEmployeeToWorkspace(String employeeId, String workspaceId);
    Employee disconnectEmployeeToWorkspace(String employeeId, String workspaceId);
    Set<Workspace> getWorkspacesByEmployeeId(String id);
    Employee connectEmployeeToAnyWorkspace(String id);

}