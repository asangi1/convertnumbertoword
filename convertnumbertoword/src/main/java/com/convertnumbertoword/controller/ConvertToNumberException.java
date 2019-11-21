package com.convertnumbertoword.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ConvertToNumberException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4227400452047398608L;
	
	public ConvertToNumberException() {
		// TODO Auto-generated constructor stub
	}
}