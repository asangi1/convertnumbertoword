package com.convertnumbertoword.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.convertnumbertoword.model.ConvertToNumber;
import com.convertnumbertoword.model.Error;

@RestController
@Path("/")
public class ConvertToNumberController {
    private static final String[] specialNames = {
            "",
            " thousand",
            " million",
            " billion",
            " trillion",
            " quadrillion",
            " quintillion"
        };
        
        private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
        };
        
        private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
        };
        @GetMapping("/NumberConvert/{number}")
        @Produces(value = "application/json")
        public ConvertToNumber convert(@PathVariable("number") String request) {
        	ConvertToNumber cn =new ConvertToNumber();
        	Error error =  new Error();
        	if(!isNumericOnly(String.valueOf(request))) {
        		error.setErrorCode("1001");
            	error.setErrorMessage("please provide valid number");
            	cn.setError(error);
            	return cn;
        	}else {
                       
            int number = Integer.valueOf(request);
            if (number < 0) {
            	error.setErrorCode("1002");
            	error.setErrorMessage("number should not be negative");
            	cn.setError(error);
            	return cn;
              
            }
            
            
            String current = "";
            int place = 0;
            
            do {
                int n = number % 1000;
                if (n != 0){
                    String s = convertLessThanOneThousand(n);
                    current = s + specialNames[place] + current;
                }
                place++;
                number /= 1000;
            } while (number > 0);
            
            cn.setResponse(current);
            return cn;
        	}
        }
        
        public  boolean isNumericOnly(String requestParam) {
                
        	boolean isNumaric = true;
            if (!requestParam.isEmpty()) {
                Matcher matcher = null;
                try {
                    String regex = "[0-9\\-]+";
                    Pattern pattern = Pattern.compile(regex);
                    matcher = pattern.matcher(requestParam);
                } catch (Exception ex) {
                	isNumaric = false;
                }
                return matcher.matches();
            }
           return isNumaric;
        }


		private String convertLessThanOneThousand(int number) {
            String current;
            
            if (number % 100 < 20){
                current = numNames[number % 100];
                number /= 100;
            }
            else {
                current = numNames[number % 10];
                number /= 10;
                
                current = tensNames[number % 10] + current;
                number /= 10;
            }
            if (number == 0) return current;
            return numNames[number] + " hundred" + current;
        }
        
        

}
