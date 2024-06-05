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
import org.springframework.web.bind.annotation.RestController;

import com.example.pc.model.Contact;
import com.example.pc.model.Message;
import com.example.pc.repository.ContactRepository;
import com.example.pc.repository.MessageRepository;
import com.example.pc.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private ContactService contactService;
    
    @Autowired
    private MessageRepository messageRepository;

    //private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return ResponseEntity.ok().body(contacts);
    }
    
    @PostMapping("/updatecontact/{id}")
    public ResponseEntity<String> updateProject(@ModelAttribute Contact contact,@PathVariable("id") Long id) {
    	try {
    		
    		 boolean isUpdated = contactService.updateContact(contact, id);
             
             if (isUpdated) {
                 return ResponseEntity.ok("Contact updated successfully");
             } else {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating contact");
             }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving contact"); // Return an error response
        }
    }
    
    @PostMapping("/postmessage")
    public ResponseEntity<String> postMesage(@ModelAttribute Message message) {
        try {
            boolean isSaved = contactService.saveMessage(message);
            
            if (isSaved) {
                return ResponseEntity.ok("Message sent successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
        }
    }

    @GetMapping("/getmessages")
    public ResponseEntity<List<Message>> getAllProjects() {
        List<Message> message = messageRepository.findAll();
        return ResponseEntity.ok().body(message);
    }
    
    @GetMapping("/deletemessage/{id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable("id") Long id) {
    	try {
            contactService.deleteMessageById(id);
            return ResponseEntity.ok("Project deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting project"); 
        }
    }
    
}
    

