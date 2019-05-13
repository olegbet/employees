package com.employeeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.employeeservice.interfaces.EventStreams;
import com.employeeservice.model.Employee;
import com.employeeservice.model.Event;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventService {
	private final Logger slf4jLogger = LoggerFactory.getLogger(EventService.class);
	
	private final EventStreams eventStreams;
	
	 public EventService(EventStreams eventStreams) {
	        this.eventStreams = eventStreams;
	    }
	 
	 public void sendEvent(final Event event) {
	    	
	    	slf4jLogger.info("Emp sent: "+event.toString());
	    	
	        MessageChannel messageChannel = eventStreams.outboundEvents();
	        messageChannel.send(MessageBuilder
	                .withPayload(event)
	                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	                .build());
	    }
}
