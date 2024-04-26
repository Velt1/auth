package de.ba.auth.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Project {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;

    // Constructors
    public Project() {}

    public Project(String name, String description, Date startDate, Date endDate, String status) {
        this.name = name;
        this.description = description;
        this.startDate = null;
        this.endDate = null;
        this.status = "todo";
    }

}