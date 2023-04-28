package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @Ignore
    public void saveEmployeeTest() {

        Employee employee = Employee.builder().name("Mark").country("England").build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest() {

        Employee employee = employeeRepository.findById(1).orElseThrow();

        Assertions.assertThat(employee.getId()).isEqualTo(1);

    }

    @Test
    @Order(3)
    public void getListOfEmployeeTest() {

        List<Employee> employeesList = employeeRepository.findAll();

        Assertions.assertThat(employeesList.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {

        Employee employee = employeeRepository.findById(1).get();

        employee.setName("Martin");
        Employee employeeUpdated = employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        Employee employee = employeeRepository.findById(1).get();

        employeeRepository.delete(employee);

        Employee employee1 = null;

        Optional<Employee> optionalAuthor = Optional.ofNullable(employeeRepository.findByName("Martin"));

        if (optionalAuthor.isPresent()) {
            employee1 = optionalAuthor.get();
        }

        Assertions.assertThat(employee1).isNull();
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void findEmployeeByCountryReturnsEmptyListTest() {
        List<Employee> employees = employeeRepository.findEmployeeByCountry("Ukraine");
        assertTrue(employees.isEmpty());
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void findEmployeeByCountryReturnsListEmployeesTest(){
        Employee employee1 = Employee.builder().name("Mark").country("England").build();
        employeeRepository.save(employee1);

        Employee employee2 = Employee.builder().name("Anna").country("Ukraine").build();
        employeeRepository.save(employee2);

        Employee employee3 = Employee.builder().name("Mykhailo").country("Ukraine").build();
        employeeRepository.save(employee3);

        List<Employee> employeesUkrainian = employeeRepository.findEmployeeByCountry("Ukraine");
        assertEquals(employeesUkrainian.size(), 2);
        assertEquals(employee2.getName(), employeesUkrainian.get(0).getName());
        assertEquals(employee3.getName(), employeesUkrainian.get(1).getName());
    }

}
