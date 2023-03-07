package com.rasyidapp.springrestapi.controller;

import com.rasyidapp.springrestapi.model.Employee;
import com.rasyidapp.springrestapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

//    @Value("${app.name}")
//    private String appName;
//
//    @Value("${app.version}")
//    private String appVersion;
//
//    @GetMapping("/version")
//    public String getAppDetails() {
//        return appName+ " - "+appVersion;
//    }

    @GetMapping( "/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
    }

    @GetMapping( "/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getOneEmployee(id), HttpStatus.OK);
    }

    @GetMapping( "/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<>(service.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping( "/employees/filteramelocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location){
        return new ResponseEntity<>(service.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping( "/employees/keyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String keyword){
        return new ResponseEntity<>(service.getEmployeeByKeyword(keyword), HttpStatus.OK);
    }

    @PostMapping( "/employees")
    public ResponseEntity<Employee> saveEmployees(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(service.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping( "/employees/{id}")
    public ResponseEntity<Employee> updateEmployees(@PathVariable Long id, @RequestBody Employee employee){
        employee.setId(id);
        return new ResponseEntity<>(service.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping( "/employees")
    public void removeEmployee(@RequestParam Long id){
        service.removeEmployee(id);
    }

    @DeleteMapping( "/employees/delete")
    public ResponseEntity<String> deleteByName(@RequestParam String name){
        return new ResponseEntity<>(service.deleteEmployeeByName(name) + " employee telah terhapus", HttpStatus.OK);
    }
}
