package com.rasyidapp.springrestapi.service;

import com.rasyidapp.springrestapi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getEmployees();
    Employee saveEmployee(Employee employee);
    Employee getOneEmployee(Long id);
    void removeEmployee(Long id);
    Employee updateEmployee(Employee employee);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByNameAndLocation(String name, String location);
    List<Employee> getEmployeeByKeyword(String keyword);
    Integer deleteEmployeeByName(String name);
}
