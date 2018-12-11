package com.gys.ripley.ms.facade;

import static com.gys.ripley.commons.FunctionsUtil.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gys.ripley.commons.ReflectionUtil;
import com.gys.ripley.commons.exception.CommonsException;
import com.gys.ripley.ms.commons.ProcedureParams;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dto.ListaSesionOutRO;
import com.gys.ripley.ms.dto.SesionOutRO;
import com.gys.ripley.ms.exception.DataBaseException;

public class SessionFacade {

	public ListaSesionOutRO populateManifiestoOutRO(ListaSesionOutRO listaSessionOutRO, ProcedureUtil procedureUtil)
			throws CommonsException {

		if (isEmpty(procedureUtil)) {
			return null;
		}
		if (isEmpty(listaSessionOutRO)) {
			listaSessionOutRO = new ListaSesionOutRO();
		}

		processParams(listaSessionOutRO, procedureUtil);

		if (procedureUtil.hasCursor()) {

			ResultSet rs = (ResultSet) procedureUtil.getProcedureParamCursor().getValue();
			List<SesionOutRO> sessionListOutRO = new ArrayList<>();
			
			try {
				
				if( isEmpty(rs) ) {
					procedureUtil.closeSession();
					return listaSessionOutRO;
				}

				while ( rs.next()) {
					SesionOutRO moro = new SesionOutRO();
					moro.setMensaje( toStr(rs.getString("MENSAJE")) );
					sessionListOutRO.add(moro);
				}
				
				rs.close();
				procedureUtil.closeSession();

			} catch (SQLException e) {
				new CommonsException(e.getErrorCode(), e);
			} catch (DataBaseException e) {
				new CommonsException(e.getCod(), e);
			}

			listaSessionOutRO.setSesiones(sessionListOutRO);

		}

		return listaSessionOutRO;
	}

	private void processParams(Object obj, ProcedureUtil procedureUtil) throws CommonsException {

		if (procedureUtil.hasParamsOut()) {
			ReflectionUtil ru = new ReflectionUtil();

			for (ProcedureParams pp : procedureUtil.getProcedureParamsOut()) {
				ru.setValueInField(obj, pp.getVarNameOut(), pp.getValue());
			}
		}

	}

}
