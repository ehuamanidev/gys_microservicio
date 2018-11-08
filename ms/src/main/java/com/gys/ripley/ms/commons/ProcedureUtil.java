package com.gys.ripley.ms.commons;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

public class ProcedureUtil {
	
	private String procedureName;
	private List<ProcedureParams> procedureParams;
	
	public ProcedureUtil(String procedureName) {
		this.procedureName = procedureName;
		this.procedureParams = new ArrayList<>();
	}
	
	public void addParamProcedure ( Object value, ParameterMode paramMode, Class<?> clazz, int parameterOrder ) {
		procedureParams.add( new ProcedureParams(value, paramMode, clazz, parameterOrder));
	}
	
	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public List<ProcedureParams> getProcedureParams() {
		return procedureParams;
	}

	public void setProcedureParams(List<ProcedureParams> procedureParams) {
		this.procedureParams = procedureParams;
	}
	
}
