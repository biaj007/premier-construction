package com.example.pc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pc.model.Admin;
import com.example.pc.model.Header;
import com.example.pc.repository.AdminRepository;
import com.example.pc.repository.HeaderRepository;
import com.example.pc.service.AdminService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private HeaderRepository headerRepository;
    
    @Autowired
    private AdminService headerService;

    //private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @PostMapping("/checklogin")
    public ResponseEntity<?> login(@RequestBody Admin login) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(login.getApass().getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        String hashedPassword = hexString.toString();

        Admin existingLogin = adminRepository.findByAuserAndApass(login.getAuser(), hashedPassword);
        if (existingLogin != null) {
            return ResponseEntity.ok(existingLogin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    
    @GetMapping("/getheader")
    public ResponseEntity<List<Header>> getHeader() {
        List<Header> header = headerRepository.findAll();
        return ResponseEntity.ok().body(header);
    }
    
    @PostMapping("/updateheader/{id}")
    public ResponseEntity<String> updateHeader(@ModelAttribute Header header,@PathVariable("id") Long id,
    		               @RequestParam(value = "bgimage", required = false) MultipartFile file,
    		               @RequestParam(value = "bgimage1", required = false) MultipartFile file1,
    		               @RequestParam(value = "bgimage2", required = false) MultipartFile file2) {
    	try {
    		
    		 boolean isUpdated = headerService.updateHeader(header, id, file, file1, file2);
             
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
    
}
    

