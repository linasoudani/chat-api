package com.example.demo.service;

import java.util.List;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class MessageServiceImp implements MessageService {
	

	 private static final String REDIS_CACHE_VALUE = "message";
	 
		@Autowired
		MessageRepository rp;
		
		@Override
		//@CachePut(value = REDIS_CACHE_VALUE, key = "#message.id")

		@CacheEvict(value = REDIS_CACHE_VALUE, allEntries = true)
		public boolean saveMessage(Message message) {
			rp.save(message);
			return true;
		}

		@Override
	    @Cacheable(value= REDIS_CACHE_VALUE)

		public List<Message> findAllMessage() {
	  
			return rp.findAll();
		}

		@Override
	
	    @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
		public Message findMessageById(int id) {
           Message message=null;
			Optional<Message> MessageResponse =  rp.findById(id);
			if (MessageResponse.isPresent()) {
			message	 = MessageResponse.get();
			} else {
				throw new RuntimeException("Record not found");
			}
			return message;
		}

		@Override
		@Caching(evict = { @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id"),
				@CacheEvict(value = REDIS_CACHE_VALUE, allEntries = true) })
		
		public boolean deleteMessage(int id) {
			 rp.deleteById(id);
			 return true;
		}

		@Override
	//	@CachePut(value = REDIS_CACHE_VALUE , key = "#id")
		@CacheEvict(value = REDIS_CACHE_VALUE, allEntries = true)
		public boolean updateUser(int id, Message message) {
			Message message1 =findMessageById(id);
		message1.setUserName(message.getUserName());
		message1.setMessage(message.getMessage());	
			rp.save( message1);
			return true;
		}

}
