package com.jrp.pma.other;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class TimeTracking
{
	
	// VARIABLES
    private LocalDateTime startTime , endTime;
    private long dayDifference , hourDifference , minuteDifference , secondDifference;
    private String duration;

    // CONSTRUCTORS
    
    
    
    public TimeTracking(String startTime)
    {
    	this.startTime = this.convertStringToLocalDateTime(startTime);
    }
    
    public TimeTracking()
    {
		
	}

	public TimeTracking(LocalDateTime startTime, LocalDateTime endTime) 
    {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		calculateDifference();
	}

    
    // CONVERTER
    
    public String getStartTimeAsString()
    {
    	return convertLocalDateTimeToString(startTime);
    }
    
    public String getEndTimeAsString()
    {
    	return convertLocalDateTimeToString(endTime);
    }
    
    public String convertLocalDateTimeToString(LocalDateTime localDateTime)
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }
    
    public LocalDateTime convertStringToLocalDateTime(String date)
    {
    	if(date.equals(""))
    		return null;
    	
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        
        return dateTime;
    }
    
    private String getResultTime()
    {
    	int seconds = (int) getSecondDifference();
    	
        int hours = seconds / 3600;
        seconds %= 3600;
        
        int minutes = seconds / 60;
        seconds %= 60;
        
        return hours + ":" + minutes + ":" + seconds;
    }
    
  
    // MAIN FUNCTIONS
    
    public void startTracking()
    {
        startTime = LocalDateTime.now();
    }
    
    public void stopTracking()
    {
        endTime = LocalDateTime.now();
        calculateDifference();
        this.setDuration(getResultTime());
    }

    private void calculateDifference()
    {
    	if((startTime == null))
    	{
    		System.out.println("Eror");
    		return;
    	}
        dayDifference = ChronoUnit.DAYS.between(startTime, endTime);
        hourDifference = ChronoUnit.HOURS.between(startTime, endTime);
        minuteDifference = ChronoUnit.MINUTES.between(startTime, endTime);
        secondDifference = ChronoUnit.SECONDS.between(startTime, endTime);
    }
    
    // Style
    
    
    // GETTERS
    
    
    public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
    
    public LocalDateTime getStartTime() {
        return startTime;
    }

	public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getDayDifference() {
        return dayDifference;
    }

    public long getHourDifference() {
        return hourDifference;
    }

    public long getMinuteDifference() {
        return minuteDifference;
    }

    public long getSecondDifference() {
        return secondDifference;
    }
    
    
    
    
    
    
    
    
    
}
