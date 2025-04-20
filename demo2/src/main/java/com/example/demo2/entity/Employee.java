package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ ¡Clave para autogenerar el ID!
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", length = 25)
    private String lastName;

    @Column(name = "email", length = 25)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date")
    private java.sql.Date hireDate;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "commission_pct")
    private Double commissionPct;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    private Employee manager;

    public Employee() {
    }
}
