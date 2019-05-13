package com.employeeservice.web;

import com.employeeservice.exception.ResourceNotFoundException;
import com.employeeservice.interfaces.EmployeeRepository;
import com.employeeservice.interfaces.EventRepository;
import com.employeeservice.model.Employee;
import com.employeeservice.model.Event;
import com.employeeservice.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/employees")
@Api(value="Employee Management System", description="Operations pertaining to employee in Employee Management System")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	private EventService eventService;
	
	private final Logger controllerLogger = LoggerFactory.getLogger(EmployeeController.class);
	
    public EmployeeController(EventService eventService) {
    	this.eventService = eventService;
    }
    
    public void sendEvent(Event event) {
    	controllerLogger.info("Event sent");
    	
    	controllerLogger.info("Event : "+event.toString());
    	
    	eventService.sendEvent(event);
    }
    
    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found")
        })
    @GetMapping("/list")
    public List<Employee> getAllEmployees() {
    	
    	Event event = new Event();
    	event.setId(new Long(0));
    	event.setEventDescription("Employees list requested");
    	
    	sendEvent(event);
    	
    	 return employeeRepository.findAll();
    	  
    	 }
	
    @ApiOperation(value = "Get an employee by Id")
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(
    		@ApiParam(value = "Employee id", required = true) @PathVariable(value = "id") Long employeeId)
      throws ResourceNotFoundException 
    {
    	Event event = new Event();
    	event.setId(employeeId);
    	event.setEventDescription("Employee by id "+employeeId+" requested");
    	
    	sendEvent(event);
    	
     Employee employee = employeeRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));
     return ResponseEntity.ok().body(employee);
    }
    

    @ApiOperation(value = "Add an employee")
    @PostMapping("/employee")
    public Employee createEmployee(@ApiParam(value = "Employee object", required = true) @Valid @RequestBody Employee employee) {
    	controllerLogger.debug("Post employee : "+employee.toString());
    	
    	employee = employeeRepository.save(employee);
    	
    	Event event = new Event();
    	event.setEmployeeId(employee.getId());
    	event.setEventDescription("Employee by id "+employee.getId()+" created");
    	
    	sendEvent(event);
    	
    	return employee;
    }
    
    @ApiOperation(value = "Update an employee")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(
    		@ApiParam(value = "Employee Id", required = true) @PathVariable(value = "id") Long employeeId,
            @ApiParam(value = "Update employee ", required = true) @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
    		
    	Employee employee = employeeRepository.findById(employeeId)
    				.orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));
     
     employee.setEmail(employeeDetails.getEmail());
     employee.setLastName(employeeDetails.getLastName());
     employee.setFirstName(employeeDetails.getFirstName());
     
 	Event event = new Event();
 	event.setEmployeeId(employeeId);
 	event.setEventDescription("Employee by id "+employeeId+" updated");
 	
 	sendEvent(event);
     
     final Employee updatedEmployee = employeeRepository.save(employee);
     return ResponseEntity.ok(updatedEmployee);
    }
    
    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(
    		@ApiParam(value = "Employee Id", required = true) @PathVariable(value = "id") Long employeeId)
      throws ResourceNotFoundException {
    	
    	Event event = new Event();
    	event.setEmployeeId(employeeId);
     	event.setEventDescription("Employee by id "+employeeId+" deleted");
     	
     	sendEvent(event);
    	
     Employee employee = employeeRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));
     employeeRepository.delete(employee);
     Map<String, Boolean> response = new HashMap<>();
     response.put("deleted", Boolean.TRUE);
     
     return response;
    }
    
    @ApiOperation(value = "Get all employee events")
    @GetMapping("/events/{id}")
    public List<Event> getEvents(
    		@ApiParam(value = "Employee Id", required = true) @PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
   
    	return eventRepository.findByEmployeeId(employeeId);
    }
    
}
