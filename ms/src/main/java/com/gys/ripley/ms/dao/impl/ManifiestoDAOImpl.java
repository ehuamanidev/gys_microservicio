package com.gys.ripley.ms.dao.impl;

import org.springframework.stereotype.Repository;

import com.gys.ripley.commons.exception.CommonsException;
import com.gys.ripley.ms.commons.MsConfig;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dao.ManifiestoDAO;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.exception.DataBaseException;
import com.gys.ripley.ms.facade.ManifiestoFacade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.gys.ripley.ms.commons.ProcedureParams.*;

@Repository
public class ManifiestoDAOImpl extends GenericDAOImpl implements ManifiestoDAO{

	final Logger logger = LogManager.getLogger(ManifiestoDAOImpl.class);
	
	@Override
	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException{
		
		ManifiestoFacade manifiestoFacade = new ManifiestoFacade();
		
		try {
			
			ProcedureUtil pu = new ProcedureUtil( MsConfig.PRC_ES_MANIFIESTO.getValue() );
			pu.addParamProcedureInt(dto.getpSucOrigId(), IN , Double.class, 1);
			pu.addParamProcedureInt(dto.getpSucDestId(), IN, Double.class, 2);
			pu.addParamProcedureInt(dto.getpTranspId(), IN, Double.class, 3);
			pu.addParamProcedureInt(dto.getpUserDespacho(), IN, String.class, 4);
			pu.addParamProcedureInt(dto.getpTipoDestino(), IN, String.class, 5);
			pu.addParamProcedureInt(dto.getpRutDestino(), IN, String.class, 6);
			pu.addParamProcedureOut(dto.getpManifiestoId(), OUT, Double.class, 7, "pManifiestoId");
			pu.addParamProcedureOut(dto.getpErrCode(), OUT, Integer.class, 8, "pErrCode");
			pu.addParamProcedureOut(dto.getpErrMsg(), OUT, String.class, 9, "pErrMsg");
			
			ejecutarProcedimiento(pu);
			manifiestoFacade.populateManifiestoDTO(dto, pu);
			
		} catch (CommonsException e) {
			throw new DataBaseException(e.getCod(), e.getMessage());
		}
		
		return dto;
	}
	
}
