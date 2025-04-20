package com.example.demo2.controller.dto;

public class CityEmployeeCountDTO {

    private String countryId;
    private String city;
    private Long employeeCount;

    public CityEmployeeCountDTO(String countryId, String city, Long employeeCount) {
        this.countryId = countryId;
        this.city = city;
        this.employeeCount = employeeCount;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCity() {
        return city;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }
}
