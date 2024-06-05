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
import org.springframework.web.multipart.MultipartFile;

import com.example.pc.model.Services;
import com.example.pc.repository.ServiceRepository;
import com.example.pc.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private ServiceService serviceService;

    //private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping("/services")
    public ResponseEntity<List<Services>> getServices() {
        List<Services> service = serviceRepository.findAll();
        return ResponseEntity.ok().body(service);
    }
    
    @GetMapping("/services/{id}")
    public ResponseEntity<List<Services>> getProjectById(@PathVariable("id") Long id) {
        System.out.println(id);
        Optional<Services> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            List<Services> serviceList = new ArrayList<>();
            serviceList.add(service.get()); // Add the single project to the list
            return ResponseEntity.ok().body(serviceList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/addservice")
    public ResponseEntity<String> addProject(@ModelAttribute Services service,
    		               @RequestParam(value = "aimage", required = false) MultipartFile file,
    		               @RequestParam(value = "aicon", required = false) MultipartFile file1) {
    	try {
            serviceService.saveService(service, file, file1);
            return ResponseEntity.ok("Service saved successfully"); // Return a success response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving service"); // Return an error response
        }
    }
    
    @PostMapping("/updateservice/{id}")
    public ResponseEntity<String> updateProject(@ModelAttribute Services service,@PathVariable("id") Long id,
    		               @RequestParam(value = "aimage", required = false) MultipartFile file,
    		               @RequestParam(value = "aicon", required = false) MultipartFile file1) {
    	try {
    		
    		 boolean isUpdated = serviceService.updateService(service, id, file, file1);
             
             if (isUpdated) {
                 return ResponseEntity.ok("Service updated successfully");
             } else {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating service");
             }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving service"); // Return an error response
        }
    }
    
    @GetMapping("/deleteservice/{id}")
    public ResponseEntity<String> deleteProjectsById(@PathVariable("id") Long id) {
    	try {
            serviceService.deleteProjectById(id);
            return ResponseEntity.ok("Service deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting service"); 
        }
    }
}
