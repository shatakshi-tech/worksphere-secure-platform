package com.worksphere.service;

import com.worksphere.entity.Department;
import com.worksphere.entity.Employee;
import com.worksphere.repository.EmployeeRepository;
import com.worksphere.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // Constructor Injection
    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // Save Employee with proper department fetch
    public Employee saveEmployee(Employee employee) {

        // Step 1: Get department ID from request
        Long deptId = employee.getDepartment().getId();

        // Step 2: Fetch full department from DB
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Step 3: Set full department object
        employee.setDepartment(department);

        // Step 4: Save employee
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
