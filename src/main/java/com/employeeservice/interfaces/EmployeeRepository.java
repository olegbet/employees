package com.employeeservice.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.employeeservice.model.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	    @Query(value = "SELECT * FROM employees e where e.email like :email", nativeQuery=true) 
	    public Optional<Employee> findByEmail(String email);
}
