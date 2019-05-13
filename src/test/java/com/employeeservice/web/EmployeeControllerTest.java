package com.employeeservice.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.skyscreamer.jsonassert.JSONAssert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import com.employeeservice.model.Employee;
import com.employeeservice.web.EmployeeController;
import com.employeeservice.interfaces.EmployeeRepository;


@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class EmployeeControllerTest {

	private static final ObjectMapper om = new ObjectMapper();
	
    @Autowired
    private TestRestTemplate restTemplate;
	
    @MockBean
    private EmployeeRepository mockERepository;
	
    @Before
    public void init() {
    	
        Employee employee = new Employee("John", "Snow", "john.snow@gmail.com");
        when(mockERepository.findByEmail("john.snow@gmail.com")).thenReturn(Optional.of(employee));
    }
    
	@Test
	public void testGetEmployeesList() {
		String empty = "{}";

        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/employees/list", String.class);

        printJSON(response);
        try {
        	assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        	assertEquals(HttpStatus.OK, response.getStatusCode());

        	JSONAssert.assertNotEquals(empty, response.getBody(), false);
        	
        } catch (org.json.JSONException je) {
        	je.printStackTrace();
        //} catch (AssertionError ae) {
        	//assertThat(ae.getMessage()).containsIgnoringCase(failureMessage);
        //	ae.printStackTrace();
        }
	}

	@Test
	public void testGetTaskList() {
		String empty = "{}";

        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/employees/events/{id}", String.class);

        printJSON(response);
        try {
        	assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        	assertEquals(HttpStatus.OK, response.getStatusCode());

        	JSONAssert.assertNotEquals(empty, response.getBody(), false);
        } catch (org.json.JSONException je) {
        	je.printStackTrace();
        	
        //} catch (AssertionError ae) {
            //assertThat(ae.getMessage()).containsIgnoringCase(failureMessage);
        	//ae.printStackTrace();
        }
	}
	
	private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
	
}
