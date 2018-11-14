package com.gys.ripley.commons.exception;

import com.gys.ripley.commons.ErrorMessages;

public class CommonsException extends Exception{

	private static final long serialVersionUID = 5065775152249947416L;
	
	private Exception ex;
	private Integer cod;
	private String message;
	
	public CommonsException( ErrorMessages err, Exception ex ) {
		this.setCod(err.getErrorCode());
		this.setMessage(err.getErrorMessage());
		this.setEx(ex);
	}
	
	public CommonsException( Integer cod, Exception ex ) {
		this.setCod(cod);
		this.setEx(ex);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

}
