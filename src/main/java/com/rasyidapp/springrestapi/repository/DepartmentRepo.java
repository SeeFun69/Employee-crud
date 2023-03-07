package com.rasyidapp.springrestapi.repository;

import com.rasyidapp.springrestapi.model.Department;
import com.rasyidapp.springrestapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
