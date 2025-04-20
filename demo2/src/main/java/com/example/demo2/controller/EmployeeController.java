package com.example.demo2.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo2.entity.Employee;
import com.example.demo2.repository.DepartmentRepository;
import com.example.demo2.repository.EmployeeRepository;
import com.example.demo2.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String listEmployees(@RequestParam(name = "filtro", required = false) String filtro, Model model) {
        List<Employee> employees;

        if (filtro != null && !filtro.isEmpty()) {
            employees = employeeRepository.findByFirstNameContainingOrLastNameContainingOrJob_JobTitleContainingOrDepartment_DepartmentNameContainingOrDepartment_Location_CityContaining(
                    filtro, filtro, filtro, filtro, filtro
            );
        } else {
            employees = employeeRepository.findAll();
        }

        model.addAttribute("listaEmpleados", employees);
        model.addAttribute("filtro", filtro);

        return "employee/empleados"; // este path sí está correcto
    }
    @GetMapping("/crearEmpleado")
    public String mostrarFormulario(Model model) {
        Employee empleado = new Employee();
        empleado.setHireDate(new java.sql.Date(System.currentTimeMillis())); // valor por defecto hoy
        model.addAttribute("empleado", empleado);

        model.addAttribute("listaJobs", jobRepository.findAll());
        model.addAttribute("listaDepartamentos", departmentRepository.findAll());

        return "employee/crearEmpleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") Employee empleado,
                                  RedirectAttributes redirectAttributes) {
        if (empleado.getHireDate() == null) {
            empleado.setHireDate(new java.sql.Date(System.currentTimeMillis()));
        }

        employeeRepository.save(empleado);
        redirectAttributes.addFlashAttribute("message", "Empleado creado exitosamente");

        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
        employeeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Empleado eliminado exitosamente");
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Optional<Employee> empleado = employeeRepository.findById(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            model.addAttribute("listaJobs", jobRepository.findAll());
            model.addAttribute("listaDepartamentos", departmentRepository.findAll());
            return "employee/crearEmpleado";
        } else {
            return "redirect:/empleados";
        }
    }

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
        return "history/historial"; // Asegúrate de que esté en la carpeta templates/employee/
    }

}
