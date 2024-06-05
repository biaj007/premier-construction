package com.example.pc.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pc.model.Services;
import com.example.pc.repository.ServiceRepository;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public void saveService(Services service, MultipartFile file1, MultipartFile file2) {
        try {
            // Save each file to a specific directory
            String uploadDir = "../premierconstruction/react-client/public/assets/images/services/";
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            service.setImage_path(uploadDir);

            // Save file1
            if (file1 != null && !file1.isEmpty()) {
                String originalFilename1 = file1.getOriginalFilename();
                String filePath1 = uploadDir + originalFilename1;
                Path destination1 = Paths.get(filePath1);
                if (!Files.exists(destination1)) {
                Files.copy(file1.getInputStream(), destination1);
                }
                service.setImagename(originalFilename1);
            }

            // Save file2 
            if (file2 != null && !file2.isEmpty()) {
                String originalFilename2 = file2.getOriginalFilename();
                String filePath2 = uploadDir + originalFilename2;
                Path destination2 = Paths.get(filePath2);
                if (!Files.exists(destination2)) {
                 Files.copy(file1.getInputStream(), destination2);
                }
                service.setIconname(originalFilename2);
            }
            serviceRepository.save(service);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
    
    public boolean updateService(Services service,Long id, MultipartFile file1, MultipartFile file2) {
    	  
    	Optional<Services> optionalService = serviceRepository.findById(id);    	

    	if (optionalService.isPresent()) {
    	    Services serviceToUpdate = optionalService.get();
    	    serviceToUpdate.setId(id);
    	    serviceToUpdate.setService_name(service.getService_name());
    	    serviceToUpdate.setContent(service.getContent());
    	   
    	try {
            // Save each file to a specific directory
            String uploadDir = "../premierconstruction/react-client/public/assets/images/projects/";
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            serviceToUpdate.setImage_path(uploadDir);

            // Save file1
            if (file1 != null && !file1.isEmpty()) {
                String originalFilename1 = file1.getOriginalFilename();
                String filePath1 = uploadDir + originalFilename1;
                Path destination1 = Paths.get(filePath1);
                if (!Files.exists(destination1)) {
                Files.copy(file1.getInputStream(), destination1);
                }
                serviceToUpdate.setImagename(originalFilename1);
               }else {
            	   String simage=service.getSimage();
            	   serviceToUpdate.setImagename(simage);
               }

            // Save file2 
            if (file2 != null && !file2.isEmpty()) {
                String originalFilename2 = file2.getOriginalFilename();
                String filePath2 = uploadDir + originalFilename2;
                Path destination2 = Paths.get(filePath2);
                if (!Files.exists(destination2)) {
                 Files.copy(file1.getInputStream(), destination2);
                }
                serviceToUpdate.setIconname(originalFilename2);
            }else {
         	   String simage1=service.getSicon();
         	  serviceToUpdate.setIconname(simage1);
            }
                  
           serviceRepository.save(serviceToUpdate);
           return true;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        }
    	}else {
    		return false;
        }
    	
    }
    
    public void deleteProjectById(Long id) {
        serviceRepository.deleteById(id);
    }

    
}
