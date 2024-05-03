package de.ba.auth.auth.controller;

import de.ba.auth.auth.controller.svc.ProjectService;
import de.ba.auth.auth.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String viewProject(@PathVariable Long id, Model model) {
        Project project = projectService.getProject(id);  // Ensure you have this method in your service
        if (project == null) {
            return "redirect:/project";  // Redirect to listing if not found
        }
        model.addAttribute("project", project);
        return "view-project";  // Name of the Thymeleaf template for viewing a project
    }


    @PostMapping
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/project";
    }
    @PostMapping("/{id}/add-time")
    public String addProjectTime(@PathVariable Long id, @RequestParam("hours") double hours, @RequestParam("description") String description, Model model) {
        projectService.addTimeToProject(id, hours, description);
        return "redirect:/project/" + id;  // Redirect back to the project view page
    }
    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/project";  // Redirect to the project list page after deletion
    }
}
