package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.service.employee.EmployeeServiceBean;
import com.example.demowithtests.util.CopyDataException;
import com.example.demowithtests.util.ListHasNoAnyElementsException;
import com.example.demowithtests.util.ListWasDeletedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceBean service;

    @Test
    public void whenSaveEmployee_shouldReturnEmployee() throws CopyDataException {
        Employee employee = new Employee();

        employee.setName("Mark");

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getName()).isSameAs(employee.getName());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void whenGivenId_shouldReturnEmployee_ifFound() {
        Employee employee = new Employee();
        employee.setId(88);

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Employee expected = service.getById(employee.getId().toString());

        assertThat(expected).isSameAs(employee);
        verify(employeeRepository).findById(employee.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void should_throw_exception_when_employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setId(89);
        employee.setName("Mark");

        given(employeeRepository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(employee.getId().toString());
    }

    @Test(expected = ListWasDeletedException.class)
    public void should_throw_exception_when_list_was_deleted_when_deleting(){
        Employee employee1 = Employee.builder().name("Mark").country("England").build();
        employee1.setIsDeleted(true);
        employeeRepository.save(employee1);

        Employee employee2 = Employee.builder().name("Anna").country("Ukraine").build();
        employee2.setIsDeleted(true);
        employeeRepository.save(employee2);

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(employeeRepository.findEmployeeByIsDeletedIsTrue()).thenReturn(employeeList);

        service.removeAll();
    }

    @Test(expected = ListHasNoAnyElementsException.class)
    public void should_throw_exception_when_list_has_no_elements_when_deleting(){
        List<Employee> employeesEmpty = Arrays.asList();
        when(employeeRepository.findAll()).thenReturn(employeesEmpty);
        when(employeeRepository.findEmployeeByIsDeletedIsTrue()).thenReturn(employeesEmpty);

        service.removeAll();
    }

    @Test
    public void delete_all_test(){
        Employee employee1 = Employee.builder().name("Mark").country("England").build();
        employeeRepository.save(employee1);

        Employee employee2 = Employee.builder().name("Anna").country("Ukraine").build();
        employeeRepository.save(employee2);

        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeRepository.findEmployeeByIsDeletedIsTrue()).thenReturn(new ArrayList<>());

        service.removeAll();
        assertEquals(employee1.getIsDeleted(), true);
        assertEquals(employee2.getIsDeleted(), true);
        verify(employeeRepository).saveAll(employees);
    }
}
