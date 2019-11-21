package com.convertnumbertoword.model;

import java.io.Serializable;

public class ConvertToNumber implements Serializable {


	/**
	 * 
	 */
	
	private static final long serialVersionUID = 6979517036175888803L;
	
	private String response;

	private Error error;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
	
}
