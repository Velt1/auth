package de.ba.auth.auth.model;

import jakarta.persistence.*;

@Entity
public class TimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double hours;
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public TimeEntry() {}

    public TimeEntry(double hours, String description, Project project) {
        this.hours = hours;
        this.description = description;
        this.project = project;
    }

    // Getters and setters
}

