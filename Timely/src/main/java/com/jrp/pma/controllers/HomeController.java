package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jrp.pma.entities.Project;
import com.jrp.pma.other.TimeTracking;
import com.jrp.pma.repositories.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController
{	
	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping()
	public String displayHome(Model model)
	{
		addProjectsToTheModel(model);
		return "HTML/index.html";
	}

	@GetMapping("/start")
	public String startProject(Model model)
	{
		addProjectsToTheModel(model);
	    
		Project project = new Project();
		
		project.startProject();
		
		proRepo.save(project);
		
		addProjectsToTheModel(model);
		
	    return "HTML/index.html";
	    
	}
	
	@GetMapping("/stop")
	public String stopProject(Model model)
	{
		addProjectsToTheModel(model);	
		
		Project lastProject = proRepo.getLastProject();
		lastProject.stopProject();
	
		proRepo.save(lastProject);
		
	    return "HTML/index.html";
	    
	}
	
	@ResponseBody 
	@RequestMapping(value = "/stop/{projectName}") 
	@GetMapping(value = "/stop/{projectName}") 
	public ModelAndView getSearchResultViaAjax(@PathVariable(value = "projectName") String projectName , Model model) 
	{ 
		if(proRepo.count() == 0)
			return null;
		
		addProjectsToTheModel(model);
		Project lastProject = proRepo.getLastProject();
		lastProject.stopProject();
		lastProject.setName(projectName);
		proRepo.save(lastProject);
		
		System.out.println("Value is : " + projectName);
		
		
		return new ModelAndView("HTML/index.html");
	} 
	
	private void addProjectsToTheModel(Model model)
	{
		List<Project> projects = (List<Project>) proRepo.findAll();
		model.addAttribute("projects" ,projects);
	}
}
