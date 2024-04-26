package de.ba.auth.auth.controller;

import de.ba.auth.auth.controller.svc.ProjectService;
import de.ba.auth.auth.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("project", projectService.listAllProjects());
        return "project"; // view name for the project list page
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "add-project";  // This should correspond to the name of your Thymeleaf template for adding projects
    }


    @PostMapping
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/project";
    }
}
