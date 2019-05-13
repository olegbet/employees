package com.employeeservice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeservice.exception.ResourceNotFoundException;
import com.employeeservice.interfaces.DepartmentRepository;
import com.employeeservice.model.Department;
import com.employeeservice.model.Employee;
import com.employeeservice.model.Event;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/departments")
@Api(value="Departments", description="Department persistancy operations")
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final Logger controllerLogger = LoggerFactory.getLogger(DepartmentController.class);
	
	 public DepartmentController() {
	    	
	    }
	
	 @ApiOperation(value = "View a list of available departments", response = List.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success"),
	            @ApiResponse(code = 401, message = "Not authorized"),
	            @ApiResponse(code = 403, message = "Forbidden"),
	            @ApiResponse(code = 404, message = "Not found")
	        })
	    @GetMapping("/list")
	    public List<Department> getAllDepartments() {
		 
	    	 return departmentRepository.findAll();
	    	  
	    	 }
	 
	 @ApiOperation(value = "Get an department by Id")
	    @GetMapping("/department/{id}")
	    public ResponseEntity<Department> getDepartmentById(
	    		@ApiParam(value = "Department id", required = true) @PathVariable(value = "id") Long departmentId)
	    			      throws ResourceNotFoundException 
	 {
		 
		 Department department = departmentRepository.findById(departmentId)
			       .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
			     return ResponseEntity.ok().body(department);
		 
	 }
	 
	    @ApiOperation(value = "Add a department")
	    @PostMapping("/department")
	    public @Valid Department createDepartment(@ApiParam(value = "Department store in database table", required = true) @Valid @RequestBody Department department) {
	    	controllerLogger.debug("Post department : "+department.toString());
	    	
	    	return departmentRepository.save(department);
	    }
	 
	    @ApiOperation(value = "Update a department")
	    @PutMapping("/department/{id}")
	    public ResponseEntity<Department> updateDepartment(
	    		@ApiParam(value = "Department Id", required = true) @PathVariable(value = "id") Long departmentId,
	            @ApiParam(value = "Update department", required = true) @Valid @RequestBody Department departmentDetails) throws ResourceNotFoundException {
	     Department department = departmentRepository.findById(departmentId)
	       .orElseThrow(() -> new ResourceNotFoundException("Department not found :: " + departmentId));
	     department.setName(departmentDetails.getName());
	     final Department updatedDepartment = departmentRepository.save(department);
	     return ResponseEntity.ok(updatedDepartment);
	    }
	    
	    @ApiOperation(value = "Delete a department")
	    @DeleteMapping("/department/{id}")
	    public Map<String, Boolean> deleteDepartment(
	    		@ApiParam(value = "Department Id", required = true) @PathVariable(value = "id") Long departmentId)
	      throws ResourceNotFoundException {
	     Department department = departmentRepository.findById(departmentId)
	       .orElseThrow(() -> new ResourceNotFoundException("Department not found :: " + departmentId));
	     departmentRepository.delete(department);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
	    }
	 
}
