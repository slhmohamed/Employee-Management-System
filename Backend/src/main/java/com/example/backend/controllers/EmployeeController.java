package com.example.backend.controllers;

import com.example.backend.dto.EmployeeRequest;
import com.example.backend.entities.Employee;
import com.example.backend.repository.EmployeeRespository;
import com.example.backend.services.EmployeeService;
import com.example.backend.utils.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.example.backend.utils.ImageUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ServletContext context;

    private final EmployeeRespository employeeRespository;

    @GetMapping("/all")
    ResponseEntity<List<Employee>> getAll(){

        return ResponseEntity.ok(employeeService.list());
    }
    @PostMapping("/newEmployee")
    ResponseEntity<Response> createEmployee(@RequestParam("file") MultipartFile file,
                                            @RequestParam("employe") String employe) throws  Exception{
        System.out.println("Ok .............");
        EmployeeRequest newEmploye = new ObjectMapper().readValue(employe, EmployeeRequest.class);





    Employee emp=    employeeService.saveUser(newEmploye,file);
    if(emp!=null) {

        return new ResponseEntity<Response>(new Response("Employe saved"), HttpStatus.CREATED);
    }else{
        return new ResponseEntity<Response>(new Response("Employe not saved"), HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/singleEmployee/{id}")
    ResponseEntity<Employee> getSingle(@PathVariable ("id") Long id){
        System.out.println(id);
        return ResponseEntity.ok(employeeService.getUser(id));
    }
    @GetMapping("/searchEmployee/{searchkey}")
    ResponseEntity<List<Employee>> getSingle(@PathVariable ("searchkey") String searchkey){

        return ResponseEntity.ok(employeeService.searchEmploye(searchkey));
    }

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<?> deleteEmploye (@PathVariable ("id") Long id ){
        employeeService.deleteUser(id);
       return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @PutMapping("/updateEmployee/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable ("id") Long id,@RequestBody EmployeeRequest employeeRequest){
        Employee employee=employeeService.getUser(id);
        employee.setFirstname(employeeRequest.getFirstname());
        employee.setLastname(employeeRequest.getLastname());
        employee.setCompany(employeeRequest.getCompany());
        employee.setBirthday(employeeRequest.getBirthday());
        employee.setEducation(employeeRequest.getEducation());
         employee.setJobExperience(employeeRequest.getJobExperience());
        employee.setSalary(employeeRequest.getSalary());
        employee.setGender(employeeRequest.getGender());

        return ResponseEntity.ok(employeeService.updateUser(employee));


    }
    @GetMapping(path="/Imgemploye/{empId}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("empId") Long empId) throws Exception{
        Employee emp = employeeRespository.findByEmpId(empId);
        byte[] images=ImageUtils.decompressImage(emp.getImageData());
        return images;  }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class MessageResponse{
        private String msg;
    }
}
