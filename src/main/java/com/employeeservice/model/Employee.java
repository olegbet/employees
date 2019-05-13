package com.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "employees")
@ApiModel(description = "Employee details")
public class Employee {
	    
		@ApiModelProperty(notes = "Employee's department")
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "id")
		private Department department;
	
		@ApiModelProperty(notes = "Employee ID (AUTO)")
		private long id;
		
		@ApiModelProperty(notes = "Email")
		private String email;
		
		@ApiModelProperty(notes = "First name")
		private String firstName;
		
		@ApiModelProperty(notes = "Last name")
		private String lastName;
		
		@ApiModelProperty(notes = "Birthday")
		private String birthday;
		
		@ApiModelProperty(notes = "Department ID")
		private int departmentId;
	    
	    public Employee() {
	    }
	    
	    public Employee(String firstName, String lastName, String email) {
	     this.firstName = firstName;
	     this.lastName = lastName;
	     this.email = email;
	    }
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    public long getId() {
	     return id;
	    }
	    
		public void setId(long id) {
	     this.id = id;
	    }
	    @Column(name = "first_name", nullable = false)
	    public String getFirstName() {
	     return firstName;
	    }
	    public void setFirstName(String firstName) {
	     this.firstName = firstName;
	    }
	    @Column(name = "last_name", nullable = false)
	    public String getLastName() {
	     return lastName;
	    }
	    public void setLastName(String lastName) {
	     this.lastName = lastName;
	    }
	    @Column(name = "email", nullable = false, unique = true)
	    public String getEmail() {
	     return email;
	    }
	    public void setEmail(String email) {
	     this.email = email;
	    }
	    
	    public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public int getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(int departmentId) {
			this.departmentId = departmentId;
		}
		@Override
	    public String toString() {
	     return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
	       + "]";
	    }
	    
	    
}
