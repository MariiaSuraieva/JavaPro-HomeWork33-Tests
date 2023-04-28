package com.example.demowithtests.web.employee;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Workspace;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.workspace.WorkspaceResponseDto;
import com.example.demowithtests.service.employee.EmployeeService;
import com.example.demowithtests.util.UserIsNotExistException;
import com.example.demowithtests.util.config.employee.EmployeeMapper;
import com.example.demowithtests.util.config.workspace.WorkspaceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("saveEmployee() started");
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        EmployeeDto dto = employeeMapper.employeeToEmployeeDto(employeeService.create(employee));
        log.info("saveEmployee() completed, employee with name " + dto.name + " created");
        return dto;
    }

    @Override
    public List<EmployeeReadDto> getAllUsers() {
        log.info("getAllUsers() started");
        List<Employee> employees = employeeService.getAll();
        List<EmployeeReadDto> employeesReadDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesReadDto.add(
                    employeeMapper.employeeToEmployeeReadDto(employee)
            );
        }
        log.info("getAllUsers() completed, client get " + employeesReadDto.size() + " employees");
        return employeesReadDto;
    }

    @Override
    public EmployeeReadDto getEmployeeById(@PathVariable String id) {
        log.info("getEmployeeById() - start: id = {}", id);
        EmployeeReadDto readDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.getById(id)
        );
        log.info("getEmployeeById() - end: id = {}", readDto);
        return readDto;
    }

    @Override
    public EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto) throws UserIsNotExistException {
        log.info("refreshEmployee() started");
        Integer parseId = Integer.parseInt(id);
        EmployeeReadDto readDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.updateById(parseId, employeeMapper.employeeDtoToEmployee(employeeDto)));
        log.info("refreshEmployee() completed: employee with id " + id + " refreshed");
        return readDto;
    }

    @Override
    public void removeEmployeeById(@PathVariable String id) {
        log.info("removeEmployee() started");
        Integer parseId = Integer.parseInt(id);
        employeeService.removeById(parseId);
        log.info("removeEmployee() completed: employee with id " + id + "deleted");
    }

    @Override
    public void removeAllUsers() {
        log.info("removeAllUsers() started");
        employeeService.removeAll();
        log.info("removeAllUsers() completed: all employees were deleted");
    }

    @Override
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        log.info("sendEmailByCountry() started");
        employeeService.sendEmailByCountry(country, text);
        log.info("sendEmailByCountry() completed: all users from country- " + country + ", get emails");
    }

    @Override
    public void sendEmailByCity(@RequestParam String cities, @RequestBody String text) {
        log.info("sendEmailByCity() started");
        employeeService.sendEmailByCity(cities, text);
        log.info("sendEmailByCity() completed: all users from cities- " + cities + ", get emails");
    }

    @Override
    public void fillingDataBase(@PathVariable String quantity) {
        log.info("fillingDataBase() started");
        employeeService.fillingDataBase(quantity);
        log.info("fillingDateBase() completed: database was filling by " + quantity + " employees");
    }

    @Override
    public void updateByCountryFully(@RequestParam String countries) {
        log.info("updateByCountryFully() started");
        employeeService.updaterByCountryFully(countries);
        log.info("updateBuCountryFully() completed: all employees randomly changed their countries, but slowly");
    }

    @Override
    public void updateByCountrySmart(@RequestParam String countries) {
        log.info("updateByCountrySmart() started");
        employeeService.updaterByCountrySmart(countries);
        log.info("updateByCountrySmart() completed: all employees randomly changed their countries fastly");
    }

    @Override
    public void replaceNull() {
        log.info("rapleceNull() started");
        employeeService.processor();
        log.info("raplaceNull() completed: all nulls were replaced");
    }

    @Override
    public void emailSenderPhotoChange() {
        log.info("emailSenderPhotoChange() started");
        employeeService.emailSenderPhotoChange();
        log.info("emailSenderPhotoChange() completed: all employees, who have to change photos, get emails");
    }

    @Override
    public void emailSenderWhoMovedFromUkraine(@RequestParam String country) {
        log.info("emailSenderWhoMovedFromUkraine() started");
        employeeService.emailSenderWhoMovedFromUkraine(country);
        log.info("emailSenderWhoMovedFromUkraine() completed: all employees moved from Ukraine get emails");
    }

    @Override
    public EmployeeReadDto addPassport(Integer employeeId, Integer passportId) {
        Employee employee = employeeService.addPassport(employeeId, passportId);
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(employee);
        return employeeReadDto;
    }


    public EmployeeReadDto addPassportSafely(Integer employeeId, Integer passportId){
        Employee employee = employeeService.addPassport(employeeId, passportId);
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(employee);
        return employeeReadDto;
    }

    @Override
    public EmployeeReadDto addPassport(Integer employeeId) {
        log.info("employeeController addPassport() started");
        Employee employee = employeeService.addPassport(employeeId);
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(employee);
        log.info("employeeController addPassport() completed");
        return employeeReadDto;
    }

    @Override
    public EmployeeReadDto connectEmployeeToWorkspace(String employeeId, String workspaceId) {
        log.info("employeeController connectEmployeeToWorkspace() started");
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.connectEmployeeToWorkspace(employeeId, workspaceId)
        );
        log.info("employeeController connectEmployeeToWorkspace() completed");
        return employeeReadDto;
    }

    @Override
    public EmployeeReadDto disconnectEmployeeToWorkspace(String employeeId, String workspaceId) {
        log.info("employeeController disconnectEmployeeToWorkspace() started");
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.disconnectEmployeeToWorkspace(employeeId, workspaceId)
        );
        log.info("employeeController disconnectEmployeeToWorkspace() completed");
        return employeeReadDto;
    }

    @Override
    public Set<WorkspaceResponseDto> getWorkspacesByEmployeeId(String employeeId) {
        log.info("employeeController getWorkspacesByEmployeeId() started");
        Set<WorkspaceResponseDto> workspaceResponseDtoList = new HashSet<>();
        Set<Workspace> workspaceList = employeeService.getWorkspacesByEmployeeId(employeeId);
        for(Workspace workspace:workspaceList){
            workspaceResponseDtoList.add(workspaceMapper.fromEntity(workspace));
        }
        log.info("employeeController getWorkspacesByEmployeeId() completed");
        return workspaceResponseDtoList;
    }

    @Override
    public EmployeeReadDto connectEmployeeToAnyWorkspace(String employeeId) {
        log.info("employeeController connectEmployeeToAnyWorkspace() started");
        EmployeeReadDto employeeReadDto = employeeMapper.employeeToEmployeeReadDto(
                employeeService.connectEmployeeToAnyWorkspace(employeeId)
        );
        log.info("employeeController connectEmployeeToAnyWorkspace() completed");
        return employeeReadDto;
    }
}