package com.example.pc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pc.model.Project;
import com.example.pc.repository.ProjectRepository;
import com.example.pc.service.ProjectService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectService projectService;
    
    //private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        Pageable pageable = PageRequest.of(0, 6); // Limiting to top 6 projects
        List<Project> projects = projectRepository.findByIsfeaturedOrderByDateDesc(1, pageable);
        return ResponseEntity.ok().body(projects);
    }
    
    @GetMapping("/projectsAll")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectRepository.findByIsfeatured(1);
        return ResponseEntity.ok().body(projects);
    }
    
    @GetMapping("/projects/{id}")
    public ResponseEntity<List<Project>> getProjectById(@PathVariable("id") Long id) {
        System.out.println(id);
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            List<Project> projectList = new ArrayList<>();
            projectList.add(project.get()); // Add the single project to the list
            return ResponseEntity.ok().body(projectList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/filterprojects/{inputquery}")
    public ResponseEntity<List<Project>> getProjectByFilter(@PathVariable("inputquery") String inputquery) {
        List<Project> filteredProjects = projectRepository.filterProjects(inputquery);
        if (!filteredProjects.isEmpty()) {
            return ResponseEntity.ok().body(filteredProjects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/adminprojects")
    public ResponseEntity<List<Project>> getAdminProjects() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok().body(projects);
    }
    
    @PostMapping("/addproject")
    public ResponseEntity<String> addProject(@ModelAttribute Project project,
    		               @RequestParam(value = "pimage", required = false) MultipartFile file,
    		               @RequestParam(value = "pimage1", required = false) MultipartFile file1,
                           @RequestParam(value = "pimage2", required = false) MultipartFile file2,
                           @RequestParam(value = "pimage3", required = false) MultipartFile file3,
                           @RequestParam(value = "pimage4", required = false) MultipartFile file4,
                           @RequestParam(value = "pimage5", required = false) MultipartFile file5,
                           @RequestParam(value = "pimage6", required = false) MultipartFile file6) {
    	try {
            projectService.saveProject(project, file, file1, file2, file3, file4, file5, file6);
            return ResponseEntity.ok("Project saved successfully"); // Return a success response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving project"); // Return an error response
        }
    }
    
    @PostMapping("/updateproject/{id}")
    public ResponseEntity<String> updateProject(@ModelAttribute Project project,@PathVariable("id") Long id,
    		               @RequestParam(value = "pimage", required = false) MultipartFile file,
    		               @RequestParam(value = "pimage1", required = false) MultipartFile file1,
                           @RequestParam(value = "pimage2", required = false) MultipartFile file2,
                           @RequestParam(value = "pimage3", required = false) MultipartFile file3,
                           @RequestParam(value = "pimage4", required = false) MultipartFile file4,
                           @RequestParam(value = "pimage5", required = false) MultipartFile file5,
                           @RequestParam(value = "pimage6", required = false) MultipartFile file6) {
    	try {
    		
    		 boolean isUpdated = projectService.updateProject(project, id, file, file1, file2, file3, file4, file5, file6);
             
             if (isUpdated) {
                 return ResponseEntity.ok("Project updated successfully");
             } else {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating project");
             }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving project"); // Return an error response
        }
    }
    
    @GetMapping("/deleteproject/{id}")
    public ResponseEntity<String> deleteProjectsById(@PathVariable("id") Long id) {
    	try {
            projectService.deleteProjectById(id);
            return ResponseEntity.ok("Project deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting project"); 
        }
    }

}
    

