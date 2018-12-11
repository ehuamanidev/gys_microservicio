package com.gys.ripley.ms.dto;

import com.gys.ripley.ms.dto.ResponseBaseDTO;
import java.util.ArrayList;
import java.util.List;

public class ListaSesionOutRO extends ResponseBaseDTO {
	public List<SesionOutRO> sesiones;
	public Integer poCntError;

	public ListaSesionOutRO() {
		super();
		sesiones = new ArrayList<>();
		//poCntError = 0;
	}

	public List<SesionOutRO> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<SesionOutRO> sesiones) {
		this.sesiones = sesiones;
	}

	public Integer getPoCntError() {
		return poCntError;
	}

	public void setPoCntError(Integer poCntError) {
		this.poCntError = poCntError;
	}

	
	
	
}
