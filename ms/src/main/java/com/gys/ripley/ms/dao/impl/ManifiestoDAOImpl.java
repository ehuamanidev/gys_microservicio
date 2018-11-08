package com.gys.ripley.ms.dao.impl;

import org.springframework.stereotype.Repository;

import com.gys.ripley.ms.commons.MsConfig;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dao.ManifiestoDAO;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.exception.DataBaseException;

import javax.persistence.ParameterMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class ManifiestoDAOImpl extends GenericDAOImpl implements ManifiestoDAO{

	final Logger logger = LogManager.getLogger(ManifiestoDAOImpl.class);
	
	@Override
	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException{
		
		ProcedureUtil pu = new ProcedureUtil( MsConfig.PRC_ES_MANIFIESTO.getValue() );
		pu.addParamProcedure(dto.getpSucOrigId(), ParameterMode.IN, Double.class, 1);
		pu.addParamProcedure(dto.getpSucDestId(), ParameterMode.IN, Double.class, 2);
		pu.addParamProcedure(dto.getpUserDespacho(), ParameterMode.IN, String.class, 2);
		pu.addParamProcedure(dto.getpTipoDestino(), ParameterMode.IN, String.class, 3);
		pu.addParamProcedure(dto.getpManifiestoId(), ParameterMode.OUT, Double.class, 4);
		pu.addParamProcedure(dto.getpErrCode(), ParameterMode.OUT, Double.class, 5);
		pu.addParamProcedure(dto.getpErrMsg(), ParameterMode.OUT, Double.class, 6);
		
		ejecutarProcedimiento(pu);
		
		return null;
	}
	
}
