package com.example.pc.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pc.model.Header;
import com.example.pc.repository.HeaderRepository;

@Service
public class AdminService {

    @Autowired
    private HeaderRepository headerRepository;

   
    public boolean updateHeader(Header header,Long id, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
    	  
    	Optional<Header> optionalHeader = headerRepository.findById(id);    	

    	if (optionalHeader.isPresent()) {
    		Header headerToUpdate = optionalHeader.get();
    		headerToUpdate.setId(id);
    		headerToUpdate.setStext(header.getStext());
    		headerToUpdate.setBtext(header.getBtext());
    		headerToUpdate.setStext1(header.getStext1());
    		headerToUpdate.setBtext1(header.getBtext1());
    		headerToUpdate.setStext2(header.getStext2());
    		headerToUpdate.setBtext2(header.getBtext2());
    	   
    	try {
            // Save each file to a specific directory
            String uploadDir = "../premierconstruction/react-client/public/assets/images/bgimages/";
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            headerToUpdate.setImagepath(uploadDir);

            // Save file1
            if (file1 != null && !file1.isEmpty()) {
                String originalFilename1 = file1.getOriginalFilename();
                String filePath1 = uploadDir + originalFilename1;
                Path destination1 = Paths.get(filePath1);
                if (!Files.exists(destination1)) {
                Files.copy(file1.getInputStream(), destination1);
                }
                headerToUpdate.setImagename(originalFilename1);
               }else {
            	   String bgimage=header.getSbgimage();
            	   headerToUpdate.setImagename(bgimage);
               }

            // Save file2 
            if (file2 != null && !file2.isEmpty()) {
                String originalFilename2 = file2.getOriginalFilename();
                String filePath2 = uploadDir + originalFilename2;
                Path destination2 = Paths.get(filePath2);
                if (!Files.exists(destination2)) {
                 Files.copy(file1.getInputStream(), destination2);
                }
                headerToUpdate.setImagename1(originalFilename2);
            }else {
         	   String bgimage1=header.getSbgimage1();
         	  headerToUpdate.setImagename1(bgimage1);
            }
            // Save file3
            if (file2 != null && !file2.isEmpty()) {
                String originalFilename2 = file2.getOriginalFilename();
                String filePath2 = uploadDir + originalFilename2;
                Path destination2 = Paths.get(filePath2);
                if (!Files.exists(destination2)) {
                 Files.copy(file1.getInputStream(), destination2);
                }
                headerToUpdate.setImagename2(originalFilename2);
            }else {
         	   String bgimage2=header.getSbgimage2();
         	  headerToUpdate.setImagename2(bgimage2);
            }
                        
           headerRepository.save(headerToUpdate);
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
        
}
