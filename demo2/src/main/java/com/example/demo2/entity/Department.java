package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {

    @Id
    @Column(name = "department_id")
    private Integer id;

    @Column(name = "department_name", length = 30)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Si deseas: también puedes agregar el manager (opcional)
    // @ManyToOne
    // @JoinColumn(name = "manager_id")
    // private Employee manager;

    public Department() {
    }
}
