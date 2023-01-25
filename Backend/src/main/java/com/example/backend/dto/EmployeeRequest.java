package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class EmployeeRequest {
    private String firstname;
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String   gender;
    private String  education;
    private String  company;
    private int  jobExperience;
    private int  salary ;

    private byte[] imageData;
}
