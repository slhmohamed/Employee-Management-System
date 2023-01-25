package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private Long empId;
   private String firstname;
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String   gender;
    private String  education;
    private String  company;
    private int  jobExperience;
    private int  salary ;

    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

}
