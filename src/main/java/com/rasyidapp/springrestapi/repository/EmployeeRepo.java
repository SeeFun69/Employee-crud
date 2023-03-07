package com.rasyidapp.springrestapi.repository;

import com.rasyidapp.springrestapi.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findByNameAndLocation(String name, String location);
    List<Employee> findByNameContainingIgnoreCase(String keyword);

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE name = :name")
    Integer deleteByName(String name);
//    List<Employee> findByDepartmentName(String name);
//    @Query("FROM Employee WHERE department.name = :name")
//    List<Employee> getEmployeeByDepartmentName(String name);
}
