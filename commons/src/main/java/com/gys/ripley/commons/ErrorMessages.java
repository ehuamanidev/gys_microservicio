package com.gys.ripley.commons;

import static com.gys.ripley.commons.FunctionsUtil.*;

public enum ErrorMessages {

	FIELD_NOT_FOUND(100000, "Campo no encontrado"),
	FIELD_NOT_PERMISSION(100001, "Sin permisos para acceder al campo"),
	FIELD_ILLEGAL_ARGUMENT(100002, "Argumento no valido para la asignación del valor"),
	FIELD_ILLEGAL_ACCESS(100002, "Argumento no valido para el acceso del valor"),
	CONSTANT_CLASS_NOT_INSTANCE(100003, "Clase {0} no se debe instanciar");
	
	
	private int errorCode;
	private String errorMessage;
	
	private ErrorMessages( int errorCode, String errorMessage ) {
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String generateMessage( String... messages ) {
		
		if( !isEmpty(messages) ) {
			return this.errorMessage;
		}
		
		Integer indice = 0;
		String errorGenerate = errorMessage;
		for(String message: messages) {
			errorGenerate = errorGenerate.replace( join("{", indice++, "}") , message);
		}
		
		return errorGenerate;
	}
	
	
	
	
}
