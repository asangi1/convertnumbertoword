package com.convertnumbertoword;

  
 import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import  org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import  org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.convertnumbertoword.model.ConvertToNumber;

  
  @RunWith(SpringRunner.class)
  
  @SpringBootTest 
  public class ConvertToNumberTests {
  
  
  @Test 
  public void testNegativeNumber() throws Exception {
	  RestTemplate restTemplate = new RestTemplate();
	  final String baseUrl = "http://localhost:8080" + "/NumberConvert/-1";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<ConvertToNumber> result = restTemplate.getForEntity(uri, ConvertToNumber.class);
	    System.out.println(result);
	     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().getError().getErrorMessage().contains("number should not be negative"));

  }
  
  @Test 
  public void testInValidNumber() throws Exception {
	  RestTemplate restTemplate = new RestTemplate();
	  final String baseUrl = "http://localhost:8080" + "/NumberConvert/number";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<ConvertToNumber> result = restTemplate.getForEntity(uri, ConvertToNumber.class);
	    System.out.println(result);
	     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().getError().getErrorMessage().contains("please provide valid number"));

  }
  @Test 
  public void testValidNumber() throws Exception {
	  RestTemplate restTemplate = new RestTemplate();
	  final String baseUrl = "http://localhost:8080" + "/NumberConvert/1234567";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<ConvertToNumber> result = restTemplate.getForEntity(uri, ConvertToNumber.class);
	    System.out.println(result);
	     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());

  }
  
  
  }
 