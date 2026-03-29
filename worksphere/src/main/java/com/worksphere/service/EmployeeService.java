package com.worksphere.service;

import com.worksphere.entity.Department;
import com.worksphere.entity.Employee;
import com.worksphere.repository.DepartmentRepository;
import com.worksphere.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // 🔧 Constructor Injection
    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // ✅ Add Employee (with validation + relationship handling)
    public Employee saveEmployee(Employee employee) {

        // 🔴 Prevent duplicate employee (case-insensitive)
        if (employeeRepository.existsByNameIgnoreCase(employee.getName())) {
            throw new RuntimeException("Employee already exists");
        }

        // 🔥 Fetch department from DB using ID
        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            throw new RuntimeException("Department ID is required");
        }

        Long deptId = employee.getDepartment().getId();

        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // ✅ Set full department object
        employee.setDepartment(department);

        // 💾 Save employee
        return employeeRepository.save(employee);
    }

    // ✅ Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // ✅ Get employee by ID (optional but useful)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // ✅ Delete employee (optional)
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}