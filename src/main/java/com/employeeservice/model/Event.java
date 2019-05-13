package com.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	private long id;
	private long employeeId;
	private String eventDescription;
	
	public Event(long id, long employeeId, String eventDescription) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.eventDescription = eventDescription;
	}
	public Event() {
		
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	 @Column(name = "employee_id", nullable = true)
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employee_id) {
		this.employeeId = employee_id;
	}
	
	@Column(name = "event_description", nullable = false)
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", employee_id=" + this.employeeId + ", eventDescription=" + this.eventDescription + "]";
	}
	
	
}
