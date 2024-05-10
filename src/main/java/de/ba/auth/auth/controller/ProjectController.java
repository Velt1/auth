package de.ba.auth.auth.controller;

import de.ba.auth.auth.controller.svc.ProjectService;
import de.ba.auth.auth.controller.svc.UserService;
import de.ba.auth.auth.model.Project;
import de.ba.auth.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

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
        Project project = projectService.getProject(id);
        if (project == null) {
            return "redirect:/project";
        }
        model.addAttribute("project", project);
        return "view-project";
    }

    @PostMapping
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/project";
    }

    @PostMapping("/{id}/add-time")
    public String addProjectTime(@PathVariable Long id, @RequestParam("hours") double hours, @RequestParam("description") String description, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get logged in username

        // Fetch the user based on the username. Ensure you have a method to find a user by their username.
        User user = userService.findByUsername(username);
        System.out.println("Username: " + username);
        System.out.println("User object retrieved: " + user);


        // Call service method to add the time entry with default status set to 'PENDING'
        projectService.addTimeToProject(id, hours, description, user.getId(), "PENDING");

        return "redirect:/project/" + id;  // Redirect back to the project view page
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/project";
    }
}
