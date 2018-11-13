package com.gys.ripley.ms.commons;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.gys.ripley.commons.FunctionsUtil.*;

public class ProcedureUtil {
	
	private String procedureName;
	private List<ProcedureParams> procedureParamsOut;
	private List<ProcedureParams> procedureParamsIn;
	private ProcedureParams procedureParamCursor;
	
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
	
	public void addParamProcedureCursor ( Object value, Integer paramMode, Class<?> clazz, int parameterOrder ) {
		 procedureParamCursor = new ProcedureParams( value, clazz, parameterOrder );
	}
	
	public void addParamProcedureInt ( Object value, Integer paramMode, Class<?> clazz, String paramName ) {
		procedureParamsIn.add( new ProcedureParams(value, clazz, paramName) );
	}
	
	public void addParamProcedureOut ( Object value, Integer paramMode, Class<?> clazz, String paramName, String varNameOut ) {
		procedureParamsOut.add( new ProcedureParams(value, clazz, paramName, varNameOut) );
	}
	
	public void addParamProcedureCursor ( Object value, Integer paramMode, Class<?> clazz, String paramName ) {
		 procedureParamCursor = new ProcedureParams( value, clazz, paramName );
	}
	
	public int paramsCount() {
		int sumParams = 0;
		
		if( hasParamsOut() ) {
			sumParams += procedureParamsOut.size();
		}
		
		if( !isEmpty(procedureParamsIn) ) {
			sumParams += procedureParamsIn.size();
		}
		
		if( hasCursor() ) {
			sumParams+=1;
		}
		
		return sumParams;
	}
	
	public boolean hasCursor() {
		return !isEmpty(procedureParamCursor);
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

	public ProcedureParams getProcedureParamCursor() {
		return procedureParamCursor;
	}

	public void setProcedureParamCursor(ProcedureParams procedureParamCursor) {
		this.procedureParamCursor = procedureParamCursor;
	}
	
}
