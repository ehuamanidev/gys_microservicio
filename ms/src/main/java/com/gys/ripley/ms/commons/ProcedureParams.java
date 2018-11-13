package com.gys.ripley.ms.commons;


public class ProcedureParams {
	
	private Object value;
	private Class<?> clazz;
	private int parameterOrder;
	private String varNameOut;
	private String paramName;
	
	public static final Integer IN = 0;
	public static final Integer OUT = 1;
	public static final Integer CURSOR = 2;
	
	public ProcedureParams(Object value, Class<?> clazz, int parameterOrder) {
		init( value, clazz, parameterOrder, null);
	}
	
	public ProcedureParams(Object value, Class<?> clazz, int parameterOrder, String varNameOut) {
		init( value, clazz, parameterOrder, varNameOut);
	}
	
	
	public ProcedureParams(Object value, Class<?> clazz, String paramName) {
		init( value, clazz, paramName, null);
	}
	
	public ProcedureParams(Object value, Class<?> clazz, String paramName, String varNameOut) {
		init( value, clazz, paramName, varNameOut);
	}
	
	
	public void init(Object value, Class<?> clazz, int parameterOrder, String varNameOut) {
		this.setValue(value);
		this.setClazz(clazz);
		this.setParameterOrder(parameterOrder);
		this.varNameOut = varNameOut;
	}
	
	public void init(Object value, Class<?> clazz, String paramName, String varNameOut) {
		this.setValue(value);
		this.setClazz(clazz);
		this.setParamName(paramName);
		this.varNameOut = varNameOut;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public int getParameterOrder() {
		return parameterOrder;
	}

	public void setParameterOrder(int parameterOrder) {
		this.parameterOrder = parameterOrder;
	}

	public String getVarNameOut() {
		return varNameOut;
	}

	public void setVarNameOut(String varNameOut) {
		this.varNameOut = varNameOut;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	
}
