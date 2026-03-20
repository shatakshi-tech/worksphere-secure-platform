package com.worksphere.entity;
import jakarta.persistence.*;

@Entity              //tells Spring this is a table
public class Department {
    @Id        //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment id
    private Long id;

    private String departmentName;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;

    }
}
