package com.example.timely.model;

import com.example.timely.converters.TimeConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Project {

    @Id
    private long id;


    @Column(nullable = false)
    private boolean isDone = false;

    private String name = " ";


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private String duration;


    public Project() {

    }

    public void startProject() {
        startTime = LocalDateTime.now();
    }

    public void stopProject() {

        setEndTime();
        calculateDurationOfProject();

        this.isDone = true;

    }

    public void updateProject(){
        calculateDurationOfProject();
        this.isDone = true;
    }

    private void setEndTime(){
        endTime = LocalDateTime.now();
    }

    private void calculateDurationOfProject(){
        this.setDuration(TimeConverter.calculateDifferenceBetweenStartAndEnd(startTime , endTime));
    }


}
