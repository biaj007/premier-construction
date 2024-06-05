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

import com.example.pc.model.About;
import com.example.pc.model.Home;
import com.example.pc.repository.AboutRepository;
import com.example.pc.repository.FactRepository;
import com.example.pc.repository.HomeRepository;
import com.example.pc.service.HomeService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HomeController {
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private AboutRepository aboutRepository;
    @Autowired
    private FactRepository factRepository;
    @Autowired
    private HomeService homeService;

    //private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping("/banner")
    public ResponseEntity<List<Home.Banner>> getProjects() {
        List<Home.Banner> banner = homeRepository.findAll();
        return ResponseEntity.ok().body(banner);
    }
    
    @GetMapping("/about")
    public ResponseEntity<List<About>> getAbout() {
        List<About> banner = aboutRepository.findAll();
        return ResponseEntity.ok().body(banner);
    }
    
    @PostMapping("/updateabout/{id}")
    public ResponseEntity<String> updateAbout(@ModelAttribute About about,@PathVariable("id") Long id,
    		               @RequestParam(value = "image", required = false) MultipartFile file) {
    	try {
    		
    		 boolean isUpdated = homeService.updateAbout(about, id, file);
             
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
    
    @GetMapping("/fact")
    public ResponseEntity<Object> getStats() {
        try {
        	Map<String, Object> result = factRepository.factAreaDtls();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
