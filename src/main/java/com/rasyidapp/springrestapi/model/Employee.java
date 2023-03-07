package com.rasyidapp.springrestapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

    private Long age = 0L;

    private String location;

    @Email(message = "Please enter your email address")
    private String email;

    @NotBlank(message = "Department Tidak Boleh Kosong")
    private String department;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
