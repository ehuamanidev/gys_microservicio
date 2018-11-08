package com.gys.ripley.commons;

public class SwaggerApiMessages {
	private SwaggerApiMessages() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String MESSAGE_200 = "Información obtenida con éxito";
	public static final String MESSAGE_400 = "Sintaxis incorrecta";
	public static final String MESSAGE_401 = "No está autorizado para ver la información";	
	public static final String MESSAGE_404 = "El servicio no está disponible";
}
