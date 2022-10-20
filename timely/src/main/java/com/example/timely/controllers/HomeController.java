package com.example.timely.controllers;

import com.example.timely.model.Project;
import com.example.timely.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private Boolean isLastProjectDone = true;
    @Autowired
    ProjectRepository proRepo;

    @GetMapping()
    public String displayHome(Model model) {

        addProjectsToTheModel(model);
        checkStatusOfLastProject();

        return "HTML/index.html";
    }

    private void checkStatusOfLastProject() {
        if (proRepo.isLastProjectFinished() != null)
            isLastProjectDone = proRepo.isLastProjectFinished();
    }

    private void addProjectsToTheModel(Model model) {

        List<Project> projects = (List<Project>) proRepo.findAll();
        model.addAttribute("projects", projects);
    }

    // Starting

    @GetMapping("/start")
    public String startProject(Model model) {
        if (isLastProjectDone) {
            isLastProjectDone = false;
            saveStartedProject();
        }

        return "redirect:/";
    }

    private void saveStartedProject() {
        Project project = new Project();
        project.startProject();
        proRepo.save(project);
    }


    // Ending

    @GetMapping(value = "/stop/{projectName}")
    public String endProject(@PathVariable(value = "projectName") String projectName, Model model) {

        if (isProjectTableEmpty()) return "redirect:/";

        if (!isLastProjectDone) {
            isLastProjectDone = true;
            saveEndedProject(projectName);
        }

        return "redirect:/";
    }

    private boolean isProjectTableEmpty() {
        return proRepo.count() == 0;
    }

    private void saveEndedProject(String projectName) {

        Project lastProject = proRepo.getLastProject();

        lastProject.stopProject();
        lastProject.setName(projectName);
        lastProject.setDone(true);

        proRepo.save(lastProject);
    }

    // Deleting Project


    @GetMapping(value = "/delete")
    public String deleteProject(@RequestParam(value = "id") Long id) {

        if (isIdOfLastProject(id))
            isLastProjectDone = true;

        proRepo.deleteById(id);

        return "redirect:/";
    }

    @GetMapping(value = "/update")
    public String displayProjectUpdateForm(@RequestParam(value = "id") Long id, Model model) {

        if(isIdOfLastProject(id) && (!proRepo.isLastProjectFinished())) return "redirect:/";

        Project tempProject = proRepo.getProject(id);

        model.addAttribute("updatedProject", tempProject);

        return "HTML/update-project-form.html";
    }

    @PostMapping(value = "/save/updated")
    public String saveUpdatedProject(@ModelAttribute(value = "updatedProject") Project updatedProject, Model model) {

        updatedProject.updateProject();

        proRepo.save(updatedProject);

        return "redirect:/";
    }

    private boolean isIdOfLastProject(Long id) {
        return id == proRepo.getIdOfLastProject();
    }




}
