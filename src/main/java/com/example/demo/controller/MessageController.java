package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;

@RestController
@RequestMapping("/api/")
public class MessageController {

	  @Autowired
	    MessageService service;
	  
	  /*---------add a message -----------*/
	  
	    @PostMapping("/add")
	    public ResponseEntity<String> saveMessage(@RequestBody Message message) {
	    	 boolean result = service.saveMessage(message);
	    	  if(result)
	              return ResponseEntity.ok("Message Created Successfully!!");
	          else
	              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
	    
	    /*---------get all messages  -----------*/
	    
	    @GetMapping("/messages")
	    public ResponseEntity<List<Message>> getAllMessages() {
	        return ResponseEntity.ok(service.findAllMessage());
	    }

	    /*---------get message by id  -----------*/
	    
	    @GetMapping("/message/{id}")
	    public ResponseEntity<Message>   getMessageByID(@PathVariable int id) {
	        return ResponseEntity.ok(service.findMessageById(id));
	    }
	    /*---------delete a message  -----------*/
	    
	    @DeleteMapping("/message/{id}")
	    public ResponseEntity<String>  remove(@PathVariable int id)   {
	        boolean result = service.deleteMessage(id);
	        if(result)
	            return ResponseEntity.ok("Message deleted Successfully!!");
	        else
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	
		}
	    /*---------update  a message -----------*/
	    @PutMapping("/message/{id}")
	    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Message message) {
	        boolean result = service.updateUser(id, message);
	        if(result)
	            return ResponseEntity.ok("Message Updated Successfully!!");
	        else
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
}
