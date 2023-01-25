package com.example.backend.repository;

import com.example.backend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface EmployeeRespository extends JpaRepository<Employee,Long> {
List<Employee> findByFirstnameContaining(String searchkey);
Employee findByEmpId (Long id);

}
