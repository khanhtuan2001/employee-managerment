package com.tuan.employeemanagement.model;

import ;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")

    private Boolean gender;

    public Employees() {
    super();
    }

    public Employees(String name, String birthday, Boolean gender) {
        super();
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday)
    {

        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        //this.birthday = dateFormat.format(birthday);
        this.birthday = birthday;
    }

    public Boolean isGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
