package com.rasyidapp.springrestapi.model;

import com.rasyidapp.springrestapi.dto.EmployeeRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama Tidak Boleh Kosong")
    private String name;
    private String location;

    @OneToMany(mappedBy = "employee")
    private List<Department> department;

//    @JoinColumn(name = "department_id")
//    @OneToOne
//    private Department department;


//    private Long age = 0L;
//
//
//    @Email(message = "Please enter your email address")
//    private String email;
//
//    @NotBlank(message = "Department Tidak Boleh Kosong")
//    private String department;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public Employee(EmployeeRequest req) {
        this.name = req.getName();
        this.location = req.getLocation();
    }

}
