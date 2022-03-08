package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Message;

@Component
public interface MessageService {
	boolean saveMessage(Message message);

	List<Message> findAllMessage();

	Message findMessageById(int id);

	boolean deleteMessage(int id);

	boolean updateUser(int id, Message message);
}
