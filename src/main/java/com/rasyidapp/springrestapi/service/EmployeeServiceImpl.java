package com.rasyidapp.springrestapi.service;

import com.rasyidapp.springrestapi.model.Employee;
import com.rasyidapp.springrestapi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

//    private static List<Employee> list = new ArrayList<>();
//
//    static {
//        Employee e = new Employee();
//        e.setName("Rasyid");
//        e.setAge(28L);
//        e.setDepartment("IT");
//        e.setEmail("rasyid@gmail. com");
//        e.setLocation("Indonesia");
//        list.add(e);
//
//        e = new Employee();
//        e.setName("Bagus");
//        e.setAge(23L);
//        e.setDepartment("Kurir");
//        e.setEmail("bagus@gmail. com");
//        e.setLocation("Indonesia");
//        list.add(e);
//    }

    @Autowired
    private EmployeeRepo repo;

    @Override
    public List<Employee> getEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public Employee getOneEmployee(Long id) {
        Optional<Employee> employee = repo.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        throw new RuntimeException("Employee not found " + id);
    }

    @Override
    public void removeEmployee(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return repo.findByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Integer deleteEmployeeByName(String name) {
        return repo.deleteByName(name);
    }
}
