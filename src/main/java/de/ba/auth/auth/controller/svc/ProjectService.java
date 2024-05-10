package de.ba.auth.auth.controller.svc;

import de.ba.auth.auth.model.Project;
import de.ba.auth.auth.model.TimeEntry;
import de.ba.auth.auth.model.User;
import de.ba.auth.auth.repo.ProjectRepo;
import de.ba.auth.auth.repo.TimeEntryRepo;
import de.ba.auth.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepository;

    @Autowired
    private TimeEntryRepo timeEntryRepo;

    @Autowired
    private UserRepo userRepository;  // Ensure this is properly set up to inject the User repository

    public List<Project> listAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void addTimeToProject(Long projectId, double hours, String description, Long userId, String status) {
        Project project = projectRepository.findById(projectId).orElse(null);
        User user = userRepository.findById(userId).orElse(null); // Retrieve the user who is logging the time

        if (project != null && user != null) {
            TimeEntry timeEntry = new TimeEntry(hours, description, project, user, status);
            timeEntryRepo.save(timeEntry);  // Save the new time entry with user and status
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
