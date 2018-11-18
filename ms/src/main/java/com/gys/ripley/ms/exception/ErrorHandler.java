package com.gys.ripley.ms.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.gys.ripley.ms.dto.ResponseBaseDTO;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<ResponseBaseDTO> internalError(DataBaseException ex, WebRequest request) {
		
		ResponseBaseDTO error = new ResponseBaseDTO( ex.getCod(), ex.getMessage() );
		return new ResponseEntity<ResponseBaseDTO>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseBaseDTO> handleAll(Exception ex, WebRequest request) {
		ResponseBaseDTO error = new ResponseBaseDTO( 0, ex.getLocalizedMessage() );
		return new ResponseEntity<ResponseBaseDTO>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
}
