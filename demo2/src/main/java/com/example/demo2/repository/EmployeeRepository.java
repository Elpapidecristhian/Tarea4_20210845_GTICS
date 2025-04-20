package com.example.demo2.repository;
import com.example.demo2.controller.dto.CityEmployeeCountDTO;
import com.example.demo2.controller.dto.ManagerInfoDTO;

import com.example.demo2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameContainingOrLastNameContainingOrJob_JobTitleContainingOrDepartment_DepartmentNameContainingOrDepartment_Location_CityContaining(
            String firstName, String lastName, String jobTitle, String departmentName, String city
    );
    List<Employee> findBySalaryGreaterThan(Double salary);

    @Query("SELECT new com.example.demo2.controller.dto.CityEmployeeCountDTO(l.countryId, l.city, COUNT(e)) " +
            "FROM Employee e " +
            "JOIN e.department d " +
            "JOIN d.location l " +
            "GROUP BY l.countryId, l.city " +
            "HAVING COUNT(e) > 3")
    List<CityEmployeeCountDTO> findCitiesWithMoreThanThreeEmployees();

    @Query("SELECT new com.example.demo2.controller.dto.ManagerInfoDTO(d.departmentName, e.firstName, e.lastName, e.salary) " +
            "FROM Employee e " +
            "JOIN e.department d " +
            "JOIN e.job j " +
            "WHERE j.jobTitle LIKE %:title% AND e.hireDate <= :fechaLimite")
    List<ManagerInfoDTO> findExperiencedManagers(@Param("title") String title, @Param("fechaLimite") java.time.LocalDate fechaLimite);

}

