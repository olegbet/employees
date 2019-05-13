package com.employeeservice.config;


import com.employeeservice.interfaces.EventStreams;

import org.springframework.cloud.stream.annotation.EnableBinding;


@EnableBinding(EventStreams.class)
public class StreamsConfig {

}
