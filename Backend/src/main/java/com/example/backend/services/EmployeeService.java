package com.example.backend.services;

import com.example.backend.dto.EmployeeRequest;
import com.example.backend.entities.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> searchEmploye(String searchkey);
    public Employee getUser(Long id);
    public Employee saveUser(EmployeeRequest employee, MultipartFile file) throws IOException;
    public Employee updateUser(Employee employee);
    public List<Employee> list();
    public void deleteUser (Long userId);
    public Employee createEmployee(Employee role);

}
