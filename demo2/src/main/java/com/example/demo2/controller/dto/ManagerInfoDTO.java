package com.example.demo2.controller.dto;

public class ManagerInfoDTO {
    private String departmentName;
    private String firstName;
    private String lastName;
    private Double salary;

    public ManagerInfoDTO(String departmentName, String firstName, String lastName, Double salary) {
        this.departmentName = departmentName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    // Getters
    public String getDepartmentName() { return departmentName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Double getSalary() { return salary; }
}
