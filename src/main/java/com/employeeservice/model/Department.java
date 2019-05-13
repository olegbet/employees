package com.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "department")
@ApiModel(description = "Department details")
public class Department {
	
	@ApiModelProperty(notes = "Employees list")
	@OneToMany(mappedBy = "id")
	@JoinColumn(name = "department_id")
	private List<Employee> employees = new ArrayList<Employee>();
	
	@ApiModelProperty(notes = "Department ID")
	private long id;
	
	@ApiModelProperty(notes = "Department Name")
	private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Department() {
		super();
		
	}
	
	
	
}
