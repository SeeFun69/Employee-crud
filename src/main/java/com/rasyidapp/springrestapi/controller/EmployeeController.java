package com.rasyidapp.springrestapi.controller;

import com.rasyidapp.springrestapi.dto.EmployeeRequest;
import com.rasyidapp.springrestapi.dto.EmployeeResponse;
import com.rasyidapp.springrestapi.model.Department;
import com.rasyidapp.springrestapi.model.Employee;
import com.rasyidapp.springrestapi.repository.DepartmentRepo;
import com.rasyidapp.springrestapi.repository.EmployeeRepo;
import com.rasyidapp.springrestapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

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

//    @GetMapping( "/employees")
//    public ResponseEntity<List<Employee>> getEmployees(){
//        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
//    }

    @GetMapping( "/employees")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        List<EmployeeResponse> employeeResponse = new ArrayList<>();
        employeeList.forEach(e -> {
            EmployeeResponse response = new EmployeeResponse();
            response.setId(e.getId());
            response.setEmployeeName(e.getName());
            response.setLocation(e.getLocation());
            List<String> dept = new ArrayList<>();
            for (Department d : e.getDepartment()){
                dept.add(d.getName());
            }
            response.setDepartment(dept);
            employeeResponse.add(response);
        });

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
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
    public ResponseEntity<String> saveEmployees(@Valid @RequestBody EmployeeRequest request){
//    public ResponseEntity<Employee> saveEmployees(@Valid @RequestBody EmployeeRequest request){
//         This is One To One relationship
//        Department department = new Department();
//        department.setName(request.getDepartment());
//        departmentRepo.save(department);
//
//        Employee employee = new Employee(request);
//        employee.setDepartment(department);
//        employeeRepo.save(employee);
//
//        return new ResponseEntity<>(employee, HttpStatus.CREATED);

        Employee employee = new Employee(request);
        employeeRepo.save(employee);

        for (String s : request.getDepartment()){
            Department d = new Department();
            d.setName(s);
            d.setEmployee(employee);

            departmentRepo.save(d);
        }

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
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

//    @GetMapping( "/employees/deptByName")
//    public ResponseEntity<List<Employee>> getEmployeeByDepatr(@RequestParam String name){
////        return new ResponseEntity<>(employeeRepo.findByDepartmentName(name), HttpStatus.OK);
//        return new ResponseEntity<>(employeeRepo.getEmployeeByDepartmentName(name), HttpStatus.OK);
//    }
}
