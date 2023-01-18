package com.exportImportexcel.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Date startsAt;
    private double salary;


}
