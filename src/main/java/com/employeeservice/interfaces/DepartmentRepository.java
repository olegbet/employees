package com.employeeservice.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeservice.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
