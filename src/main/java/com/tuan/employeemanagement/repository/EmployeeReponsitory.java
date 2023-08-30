package com.tuan.employeemanagement.repository;

import com.tuan.employeemanagement.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReponsitory extends JpaRepository<Employees,Long> {
}
