package com.example.demo2.controller.search;

import com.example.demo2.controller.dto.CityEmployeeCountDTO;
import com.example.demo2.controller.dto.ManagerInfoDTO;
import com.example.demo2.entity.Employee;
import com.example.demo2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reportes")

public class SearchController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Vista principal de reportes
    @GetMapping
    public String mostrarMenuReportes() {
        return "search/reportes"; // Este archivo debe existir: templates/search/reportes.html
    }

    // Reporte 1: Empleados con salario mayor a 15000
    @GetMapping("/altosalario")
    public String empleadosAltoSalario(Model model) {
        List<Employee> empleados = employeeRepository.findBySalaryGreaterThan(15000.0);
        model.addAttribute("empleados", empleados);
        return "search/reportes_altosalario";
    }
    @GetMapping("/departamentos")
    public String reporteCiudades(Model model) {
        List<CityEmployeeCountDTO> reporte = employeeRepository.findCitiesWithMoreThanThreeEmployees();
        model.addAttribute("reporteCiudades", reporte);
        return "search/reportes_departamentos";
    }


    @GetMapping("/gerentes")
    public String mostrarGerentesConExperiencia(Model model) {
        LocalDate hace5Anios = LocalDate.now().minusYears(5);
        List<ManagerInfoDTO> lista = employeeRepository.findExperiencedManagers("Manager", hace5Anios);
        model.addAttribute("gerentes", lista);
        return "search/reportes_gerentes";
    }




}
