package com.gys.ripley.ms.facade;

import com.gys.ripley.ms.commons.ProcedureParams;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dto.ManifiestoDTO;

import static com.gys.ripley.commons.FunctionsUtil.*;

import com.gys.ripley.commons.ReflectionUtil;
import com.gys.ripley.commons.exception.CommonsException;

public class ManifiestoFacade {

	public ManifiestoDTO populateManifiestoDTO( ManifiestoDTO manifiestoDTO, ProcedureUtil procedureUtil ) throws CommonsException {
		
		if( isEmpty(procedureUtil) ) { return null; }
		if( isEmpty(manifiestoDTO) ) { manifiestoDTO = new ManifiestoDTO(); }
		
		if( procedureUtil.hasParamsOut() ) {
			ReflectionUtil ru = new ReflectionUtil();
			
			for( ProcedureParams pp : procedureUtil.getProcedureParamsOut() ) {
				ru.setValueInField(manifiestoDTO, pp.getVarNameOut(), pp.getValue());
			}
		}
		
		return manifiestoDTO;
	}
	
}
