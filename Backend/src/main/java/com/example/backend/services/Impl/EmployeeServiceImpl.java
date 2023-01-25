package com.example.backend.services.Impl;

import com.example.backend.dto.EmployeeRequest;
import com.example.backend.entities.Employee;
import com.example.backend.repository.EmployeeRespository;
import com.example.backend.services.EmployeeService;
import com.example.backend.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRespository employeeRespository;
    @Override
    public List<Employee> searchEmploye(String searchkey) {
        return employeeRespository.findByFirstnameContaining(searchkey);
    }

    @Override
    public Employee getUser(Long id){
       return employeeRespository.findByEmpId(id);

    }

    @Override
    public Employee saveUser(EmployeeRequest employee, MultipartFile file) throws IOException {
        Employee emp=Employee.build(0L, employee.getFirstname(), employee.getLastname(), employee.getBirthday(),
                employee.getGender(), employee.getEducation(), employee.getCompany(), employee.getJobExperience(), employee.getSalary(),
                ImageUtils.compressImage(file.getBytes()));
        return employeeRespository.save(emp);
    }

    @Override
    public Employee updateUser(Employee employee) {
        return employeeRespository.save(employee);
    }

    @Override
    public List<Employee> list() {
        return employeeRespository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        employeeRespository.deleteById(id);
    }

    @Override
    public Employee createEmployee(Employee role) {
        return null;
    }
}
