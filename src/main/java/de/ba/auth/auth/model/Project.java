package de.ba.auth.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String status;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<TimeEntry> timeEntries;

    // Getters and Setters
    public Set<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(Set<TimeEntry> timeEntries) {
        this.timeEntries = timeEntries;
    }

    // Constructors
    public Project() {}

    public Project(String name, String description, Date startDate, Date endDate, String status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }


}