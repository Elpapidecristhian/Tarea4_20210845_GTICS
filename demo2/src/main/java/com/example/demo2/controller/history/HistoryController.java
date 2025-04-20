package com.example.demo2.controller.history;

import com.example.demo2.entity.Employee;
import com.example.demo2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/historial")
    public String mostrarHistorial(@RequestParam(name = "filtro", required = false) String filtro, Model model) {
        List<Employee> empleados;

        if (filtro != null && !filtro.isEmpty()) {
            empleados = employeeRepository.findByFirstNameContainingOrLastNameContainingOrJob_JobTitleContainingOrDepartment_DepartmentNameContainingOrDepartment_Location_CityContaining(
                    filtro, filtro, filtro, filtro, filtro
            );
        } else {
            empleados = employeeRepository.findAll();
        }

        model.addAttribute("listaHistorial", empleados);
        model.addAttribute("filtro", filtro);
        return "history/historial";
    }
}
