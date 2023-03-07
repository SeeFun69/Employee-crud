package com.rasyidapp.springrestapi.controller;

import com.rasyidapp.springrestapi.dto.DepartmentResponse;
import com.rasyidapp.springrestapi.model.Department;
import com.rasyidapp.springrestapi.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmnetController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/departmnet")
    private List<DepartmentResponse> getDepartments(){
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentResponse> list = new ArrayList<>();
        departments.forEach(department -> {
            DepartmentResponse response = new DepartmentResponse();
            response.setDepartmentName(department.getName());
            response.setId(department.getId());
            response.setEmployeeName(department.getEmployee().getName());
            response.setLocation(department.getEmployee().getLocation());
            list.add(response);
        });
        return list;
    }
}
