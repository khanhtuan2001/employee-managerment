package com.tuan.employeemanagement.repository;

import com.tuan.employeemanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskReponsitory extends JpaRepository<Task,Long> {
}
