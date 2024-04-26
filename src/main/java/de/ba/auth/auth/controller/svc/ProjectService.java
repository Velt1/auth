package de.ba.auth.auth.controller.svc;

import de.ba.auth.auth.model.Project;
import de.ba.auth.auth.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepository;

    public List<Project> listAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
}

