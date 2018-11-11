package com.gys.ripley.ms.commons;

import java.util.ArrayList;
import java.util.List;

import static com.gys.ripley.commons.FunctionsUtil.*;

public class ProcedureUtil {
	
	private String procedureName;
	private List<ProcedureParams> procedureParamsOut;
	private List<ProcedureParams> procedureParamsIn;
	
	public ProcedureUtil(String procedureName) {
		this.procedureName = procedureName;
		this.procedureParamsOut = new ArrayList<>();
		this.procedureParamsIn = new ArrayList<>();
	}
	
	public void addParamProcedureInt ( Object value, Integer paramMode, Class<?> clazz, int parameterOrder ) {
		procedureParamsIn.add( new ProcedureParams(value, clazz, parameterOrder) );
	}
	
	public void addParamProcedureOut ( Object value, Integer paramMode, Class<?> clazz, int parameterOrder, String varNameOut ) {
		procedureParamsOut.add( new ProcedureParams(value, clazz, parameterOrder, varNameOut) );
	}
	
	public boolean hasParams() {
		return !isEmpty(procedureParamsIn) || hasParamsOut(); 
	}
	
	public boolean hasParamsOut() {
		return !isEmpty(procedureParamsOut);
	}
	
	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public List<ProcedureParams> getProcedureParamsOut() {
		return procedureParamsOut;
	}

	public void setProcedureParamsOut(List<ProcedureParams> procedureParams) {
		this.procedureParamsOut = procedureParams;
	}

	public List<ProcedureParams> getProcedureParamsIn() {
		return procedureParamsIn;
	}

	public void setProcedureParamsIn(List<ProcedureParams> procedureParamsIn) {
		this.procedureParamsIn = procedureParamsIn;
	}
	
}
