package com.employeeservice.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeservice.model.Event;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
	@Query(value = "SELECT * FROM events e where e.employee_id = :id ORDER BY id DESC", nativeQuery=true)
	 public List<Event> findByEmployeeId(Long id);
	
	
	 
}
