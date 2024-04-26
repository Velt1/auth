package de.ba.auth.auth.controller.svc;

import de.ba.auth.auth.model.Project;
import de.ba.auth.auth.model.TimeEntry;
import de.ba.auth.auth.repo.ProjectRepo;
import de.ba.auth.auth.repo.TimeEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepository;

    @Autowired // Ensure this annotation is present
    private TimeEntryRepo timeEntryRepo; // Autowired to inject the repository

    public List<Project> listAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void addTimeToProject(Long projectId, double hours, String description) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            TimeEntry timeEntry = new TimeEntry(hours, description, project);
            timeEntryRepo.save(timeEntry);  // This will now work as timeEntryRepo is properly autowired
        }
    }
}
