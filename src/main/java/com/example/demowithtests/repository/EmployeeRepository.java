package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
//@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeeByIsDeletedIsTrue();


    //List<Employee> findEmployeeByIsDeletedNull();

    @Query(value = " select e from Employee e where e.country=:country")
    List<Employee> findEmployeeByCountry(@Param("country")String country);

    @Query(value = "select e from Employee e join e.addresses a where a.city=:city")
    List<Employee> findEmployeeByAddresses(String city);

    static void saveAllSmart(List<Employee> employees) {};
    List<Employee> findEmployeeByIsDeletedNull();
    List<Employee> findEmployeeByCountryIsContaining(String country);

    List<Employee> findByWorkspacesContaining(Workspace workspace);
}