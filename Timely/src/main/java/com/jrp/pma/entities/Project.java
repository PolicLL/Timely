package com.jrp.pma.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.jrp.pma.other.TimeTracking;

// class which represents project

@Entity
public class Project
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "project_seq")
	@SequenceGenerator(name = "project_seq", allocationSize = 1) 
	private long id; 
	
	private String name = " ";
	
	private String startTime = "" , endTime;
	
	private String duration;
	
	@Transient
	private TimeTracking timeTracking;
	
	public Project()
	{
		timeTracking = new TimeTracking();
	}

	// START and STOP Project
	
	public void startProject()
	{
		timeTracking.startTracking();
		
		this.setStartTime(timeTracking.getStartTimeAsString());
	}
	
	public void stopProject()
	{
		timeTracking = new TimeTracking(startTime);
		timeTracking.stopTracking();
		
		this.setDuration(timeTracking.getDuration());
		
		this.setEndTime(timeTracking.getEndTimeAsString());
	}
	
	// GETTERS AND SETTERS
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration + ", timeTracking=" + timeTracking + "]";
	}

	
	
	
	
	
	
	
}
