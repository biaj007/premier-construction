package com.example.pc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.pc.model.Project;
import com.example.pc.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void saveProject(Project project, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6, MultipartFile file7) {
        try {
            // Save each file to a specific directory
            String uploadDir = "../premierconstruction/react-client/public/assets/images/projects/";
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            project.setImagePath(uploadDir);

            // Save file1
            if (file1 != null && !file1.isEmpty()) {
                String originalFilename1 = file1.getOriginalFilename();
                String filePath1 = uploadDir + originalFilename1;
                Path destination1 = Paths.get(filePath1);
                if (!Files.exists(destination1)) {
                Files.copy(file1.getInputStream(), destination1);
                }
                project.setPimagename(originalFilename1);
            }

            // Save file2 
            if (file2 != null && !file2.isEmpty()) {
                String originalFilename2 = file2.getOriginalFilename();
                String filePath2 = uploadDir + originalFilename2;
                Path destination2 = Paths.get(filePath2);
                if (!Files.exists(destination2)) {
                 Files.copy(file1.getInputStream(), destination2);
                }
                project.setPimage1name(originalFilename2); // Set the original filename in the project object
            }
         // Save file3 
            if (file3 != null && !file3.isEmpty()) {
                String originalFilename3 = file3.getOriginalFilename();
                String filePath3 = uploadDir + originalFilename3;
                Path destination3 = Paths.get(filePath3);
                if (!Files.exists(destination3)) {
                   Files.copy(file3.getInputStream(), destination3);
                }
                project.setPimage2name(originalFilename3); // Set the original filename in the project object
            }
            // Save file4 
            if (file4 != null && !file4.isEmpty()) {
                String originalFilename4 = file4.getOriginalFilename();
                String filePath4 = uploadDir + originalFilename4;
                Path destination4 = Paths.get(filePath4);
                if (!Files.exists(destination4)) {
                     Files.copy(file4.getInputStream(), destination4);
                }
                project.setPimage3name(originalFilename4); // Set the original filename in the project object
            }
            
            // Save file7
            if (file5!= null && !file5.isEmpty()) {
                String originalFilename5= file5.getOriginalFilename();
                String filePath5= uploadDir + originalFilename5;
                Path destination5= Paths.get(filePath5);
                if (!Files.exists(destination5)) {
                   Files.copy(file7.getInputStream(), destination5);
                }
                project.setPimage4name(originalFilename5); // Set the original filename in the project object
            }
            
         // Save file6
            if (file6!= null && !file6.isEmpty()) {
                String originalFilename6= file6.getOriginalFilename();
                String filePath6= uploadDir + originalFilename6;
                Path destination6= Paths.get(filePath6);
                if (!Files.exists(destination6)) {
                 Files.copy(file6.getInputStream(), destination6);
                }
                project.setPimage5name(originalFilename6); // Set the original filename in the project object
            }
            
         // Save file7
            if (file7!= null && !file7.isEmpty()) {
                String originalFilename7= file7.getOriginalFilename();
                String filePath7= uploadDir + originalFilename7;
                Path destination7= Paths.get(filePath7);
                if (Files.exists(destination7)) {
                  Files.copy(file7.getInputStream(), destination7);
                }
                project.setPimage6name(originalFilename7); // Set the original filename in the project object
            }
            
            List<String> imageList = new ArrayList<>();

         // Add non-empty and unique image names to the list
         addToImageList(imageList, project.getPimagename());
         addToImageList(imageList, project.getPimage1name());
         addToImageList(imageList, project.getPimage2name());
         addToImageList(imageList, project.getPimage3name());
         addToImageList(imageList, project.getPimage4name());
         addToImageList(imageList, project.getPimage5name());
         addToImageList(imageList, project.getPimage6name());

         // Concatenate the list elements into a string
         String imageAll = String.join(",", imageList);
            
            project.setPimage_all(imageAll);
            projectRepository.save(project);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
    
    private void addToImageList(List<String> list, String value) {
        if (value != null && !value.isEmpty() && !list.contains(value)) {
            list.add(value);
        }
    }
    
    public boolean updateProject(Project project,Long id, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6, MultipartFile file7) {
    	  
    	Optional<Project> optionalProject = projectRepository.findById(id);    	

    	if (optionalProject.isPresent()) {
    	    Project projectToUpdate = optionalProject.get();
    	    projectToUpdate.setPid(id);
    	    projectToUpdate.setTitle(project.getTitle());
    	    projectToUpdate.setPcontent(project.getPcontent());
    	    projectToUpdate.setType(project.getType());
    	    projectToUpdate.setBhk(project.getBhk());
    	    projectToUpdate.setBedroom(project.getBedroom());
    	    projectToUpdate.setBathroom(project.getBathroom());
    	    projectToUpdate.setBalcony(project.getBalcony());
    	    projectToUpdate.setKitchen(project.getKitchen());
    	    projectToUpdate.setHall(project.getHall());
    	    projectToUpdate.setFloor(project.getFloor());
    	    projectToUpdate.setSize(project.getSize());
    	    projectToUpdate.setPrice(project.getPrice());
    	    projectToUpdate.setLocation(project.getLocation());
    	    projectToUpdate.setCity(project.getCity());
    	    projectToUpdate.setState(project.getState());
    	    projectToUpdate.setStatus(project.getStatus());
    	    projectToUpdate.setTotalfloor(project.getTotalfloor());
    	    projectToUpdate.setIsfeatured(project.getIsfeatured());
    	    projectToUpdate.setClient_name(project.getClient_name());
    	    projectToUpdate.setComplete_years(project.getComplete_years());
    	    projectToUpdate.setConstr_duration(project.getConstr_duration());

    	try {
            // Save each file to a specific directory
            String uploadDir = "../premierconstruction/react-client/public/assets/images/projects/";
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            projectToUpdate.setImagePath(uploadDir);

            // Save file1
            if (file1 != null && !file1.isEmpty()) {
                String originalFilename1 = file1.getOriginalFilename();
                String filePath1 = uploadDir + originalFilename1;
                Path destination1 = Paths.get(filePath1);
                if (!Files.exists(destination1)) {
                Files.copy(file1.getInputStream(), destination1);
                }
                projectToUpdate.setPimagename(originalFilename1);
               }else {
            	   String simage=project.getSimage();
            	   projectToUpdate.setPimagename(simage);
               }

            // Save file2 
            if (file2 != null && !file2.isEmpty()) {
                String originalFilename2 = file2.getOriginalFilename();
                String filePath2 = uploadDir + originalFilename2;
                Path destination2 = Paths.get(filePath2);
                if (!Files.exists(destination2)) {
                 Files.copy(file1.getInputStream(), destination2);
                }
                projectToUpdate.setPimage1name(originalFilename2);
            }else {
         	   String simage1=project.getSimage1();
         	  projectToUpdate.setPimage1name(simage1);
            }
         // Save file3 
            if (file3 != null && !file3.isEmpty()) {
                String originalFilename3 = file3.getOriginalFilename();
                String filePath3 = uploadDir + originalFilename3;
                Path destination3 = Paths.get(filePath3);
                if (!Files.exists(destination3)) {
                   Files.copy(file3.getInputStream(), destination3);
                }
                projectToUpdate.setPimage2name(originalFilename3);
            }else {
         	   String simage2=project.getSimage2();
         	  projectToUpdate.setPimage2name(simage2);
            }
            // Save file4 
            if (file4 != null && !file4.isEmpty()) {
                String originalFilename4 = file4.getOriginalFilename();
                String filePath4 = uploadDir + originalFilename4;
                Path destination4 = Paths.get(filePath4);
                if (!Files.exists(destination4)) {
                     Files.copy(file4.getInputStream(), destination4);
                }
                projectToUpdate.setPimage3name(originalFilename4);
            }else {
         	   String simage3=project.getSimage3();
         	  projectToUpdate.setPimage3name(simage3);
            }
            
            // Save file7
            if (file5!= null && !file5.isEmpty()) {
                String originalFilename5= file5.getOriginalFilename();
                String filePath5= uploadDir + originalFilename5;
                Path destination5= Paths.get(filePath5);
                if (!Files.exists(destination5)) {
                   Files.copy(file7.getInputStream(), destination5);
                }
                projectToUpdate.setPimage4name(originalFilename5); 
            }else {
         	   String simage4=project.getSimage4();
         	  projectToUpdate.setPimage4name(simage4);
            }
            
         // Save file6
            if (file6!= null && !file6.isEmpty()) {
                String originalFilename6= file6.getOriginalFilename();
                String filePath6= uploadDir + originalFilename6;
                Path destination6= Paths.get(filePath6);
                if (!Files.exists(destination6)) {
                 Files.copy(file6.getInputStream(), destination6);
                }
                projectToUpdate.setPimage5name(originalFilename6);
            }else {
         	   String simage5=project.getSimage5();
         	   projectToUpdate.setPimage5name(simage5);
            }
            
         // Save file7
            if (file7!= null && !file7.isEmpty()) {
                String originalFilename7= file7.getOriginalFilename();
                String filePath7= uploadDir + originalFilename7;
                Path destination7= Paths.get(filePath7);
                if (Files.exists(destination7)) {
                  Files.copy(file7.getInputStream(), destination7);
                }
                project.setPimage6name(originalFilename7); 
            }else {
         	   String simage6=project.getSimage6();
         	  projectToUpdate.setPimage6name(simage6);
            }
         // Before returning from updateProject method
            System.out.println("pimagename value: " + projectToUpdate.getPimagename());
              
            List<String> imageList = new ArrayList<>();

            // Add non-empty and unique image names to the list
            addToImageList(imageList, projectToUpdate.getPimagename());
            addToImageList(imageList, projectToUpdate.getPimage1name());
            addToImageList(imageList, projectToUpdate.getPimage2name());
            addToImageList(imageList, projectToUpdate.getPimage3name());
            addToImageList(imageList, projectToUpdate.getPimage4name());
            addToImageList(imageList, projectToUpdate.getPimage5name());
            addToImageList(imageList, projectToUpdate.getPimage6name());

            // Concatenate the list elements into a string
            String imageAll = String.join(",", imageList);
            
            projectToUpdate.setPimage_all(imageAll);
                        
           System.out.println(projectToUpdate);
           projectRepository.save(projectToUpdate);
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
        projectRepository.deleteById(id);
    }

    
}
