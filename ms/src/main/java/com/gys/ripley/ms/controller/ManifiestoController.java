package com.gys.ripley.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gys.ripley.commons.SwaggerApiMessages;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.dto.ManifiestoInRO;
import com.gys.ripley.ms.dto.ManifiestoListOutRO;
import com.gys.ripley.ms.exception.DataBaseException;
import com.gys.ripley.ms.services.ManifiestoService;

import org.springframework.http.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(value = "manifiesto")
public class ManifiestoController extends BaseController  {

	@Autowired
	private ManifiestoService manifiestoService;
	
	@ApiOperation(value = "Servicio que obtiene la lista de estrategias de abordaje del diagnóstico de indicadores.", response = ManifiestoDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Información obtenida con éxito"),
			@ApiResponse(code = 400, message = SwaggerApiMessages.MESSAGE_400),
			@ApiResponse(code = 401, message = SwaggerApiMessages.MESSAGE_401),
			@ApiResponse(code = 404, message = SwaggerApiMessages.MESSAGE_404) })
	@RequestMapping(value = "/crear_manifiesto/", 
					method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ManifiestoDTO listaPlanAccion(@RequestBody ManifiestoDTO dto, HttpServletResponse response,
			HttpServletRequest request){
		
		try {
			return manifiestoService.crearManifiesto(dto);
		} catch ( DataBaseException e ) {
			dto.errorException(e.getCod(), e.getMessage());
		}
		
		return dto;
	}
	
	
	@ApiOperation(value = "Permite seleccionar un manifiesto.", response = ManifiestoListOutRO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Información obtenida con éxito"),
			@ApiResponse(code = 400, message = SwaggerApiMessages.MESSAGE_400),
			@ApiResponse(code = 401, message = SwaggerApiMessages.MESSAGE_401),
			@ApiResponse(code = 404, message = SwaggerApiMessages.MESSAGE_404) })
	@RequestMapping(value = "/manifiesto_sel/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ManifiestoListOutRO listManifiestoOutRO(@RequestBody ManifiestoInRO dto, HttpServletResponse response,
			HttpServletRequest request){
		
		ManifiestoListOutRO manifiesto = new ManifiestoListOutRO();
		
		try {
			manifiesto = manifiestoService.selManifiesto(dto);
		} catch ( DataBaseException e ) {
			manifiesto.errorException(e.getCod(), e.getMessage());
		}
		
		return manifiesto;
	}
}
