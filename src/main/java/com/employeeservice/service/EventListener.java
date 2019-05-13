package com.employeeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.employeeservice.interfaces.EventRepository;
import com.employeeservice.interfaces.EventStreams;
import com.employeeservice.model.Event;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventListener {
private final Logger slf4jLogger = LoggerFactory.getLogger(EventListener.class);
	
    @Autowired
    private EventStreams outputEvent;
	
	 @Autowired
	 private EventRepository eventRepository;
	
	 @StreamListener(EventStreams.INPUT)
    public void handleEmployees(@Payload Event event) {
        
		slf4jLogger.info("Received event: {"+event.toString()+"}");
		
		eventRepository.save(event);
        
    }
}
