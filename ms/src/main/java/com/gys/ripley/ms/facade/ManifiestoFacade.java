package com.gys.ripley.ms.facade;

import com.gys.ripley.ms.commons.ProcedureParams;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.dto.ManifiestoListOutRO;
import com.gys.ripley.ms.dto.ManifiestoOutRO;

import static com.gys.ripley.commons.FunctionsUtil.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gys.ripley.commons.ReflectionUtil;
import com.gys.ripley.commons.exception.CommonsException;

public class ManifiestoFacade {

	public ManifiestoDTO populateManifiestoDTO( ManifiestoDTO manifiestoDTO, ProcedureUtil procedureUtil ) throws CommonsException {
		
		if( isEmpty(procedureUtil) ) { return null; }
		if( isEmpty(manifiestoDTO) ) { manifiestoDTO = new ManifiestoDTO(); }
		
		processParams(manifiestoDTO, procedureUtil);
		
		return manifiestoDTO;
	}
	
	public ManifiestoListOutRO populateManifiestoOutRO( ManifiestoListOutRO manifiestoDTO, ProcedureUtil procedureUtil ) throws CommonsException {
		
		if( isEmpty(procedureUtil) ) { return null; }
		if( isEmpty(manifiestoDTO) ) { manifiestoDTO = new ManifiestoListOutRO(); }
		
		processParams(manifiestoDTO, procedureUtil);
		
		if( procedureUtil.hasCursor() ) {
			
			ResultSet rs = (ResultSet) procedureUtil.getProcedureParamCursor().getValue();
			List<ManifiestoOutRO> manifiestoOutRo = new ArrayList<>();
			
			try {
				
				while( rs.next() ) {
					
					ManifiestoOutRO moro = new ManifiestoOutRO();
					moro.setManifiestoId( toLong( rs.getLong("MANIFIESTO_ID") ) );
					moro.setTranspId( rs.getLong("TRANSP_ID") );
					
					manifiestoOutRo.add(moro);
					
				}
				rs.close();
				
			} catch (SQLException e) {
				new CommonsException(e.getErrorCode(), e);
			}
			
			manifiestoDTO.setManifiestos(manifiestoOutRo);
			
		}
		
		return manifiestoDTO;
	}
	
	private void processParams( Object obj, ProcedureUtil procedureUtil ) throws CommonsException {
		
		if( procedureUtil.hasParamsOut() ) {
			ReflectionUtil ru = new ReflectionUtil();
			
			for( ProcedureParams pp : procedureUtil.getProcedureParamsOut() ) {
				ru.setValueInField( obj, pp.getVarNameOut(), pp.getValue() );
			}
		}
		
	}
	
}
