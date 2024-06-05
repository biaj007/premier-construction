package com.example.pc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.pc.model.Contact;
import com.example.pc.model.Message;
import com.example.pc.repository.ContactRepository;
import com.example.pc.repository.MessageRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MessageRepository messageRepository;


    
    public boolean updateContact(Contact contact,Long id) {
    	  
    	Optional<Contact> optionalContact = contactRepository.findById(id);    	

    	if (optionalContact.isPresent()) {
    	    Contact ContactToUpdate = optionalContact.get();
    	    ContactToUpdate.setCid(id);
    	    ContactToUpdate.setName(contact.getName());
    	    ContactToUpdate.setEmail(contact.getEmail());
    	    ContactToUpdate.setPhone(contact.getPhone());
    	    ContactToUpdate.setOffice_address(contact.getOffice_address());
    	    ContactToUpdate.setEmbed_map(contact.getEmbed_map());

    	try {
           contactRepository.save(ContactToUpdate);
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
    
    public boolean saveMessage(Message message) {
        try {
        	System.out.println(message);
            messageRepository.save(message);
            
            return true;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        }
    }
     
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }
}
