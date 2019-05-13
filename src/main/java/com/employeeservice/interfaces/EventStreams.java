package com.employeeservice.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventStreams {
	String INPUT = "employees-in";
    String OUTPUT = "employees-out";
    
    @Input(INPUT)
    SubscribableChannel inboundEvents();
    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
