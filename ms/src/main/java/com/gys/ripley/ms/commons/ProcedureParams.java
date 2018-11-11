package com.gys.ripley.ms.commons;


public class ProcedureParams {
	
	private Object value;
	private Class<?> clazz;
	private int parameterOrder;
	private String varNameOut;
	
	public static final Integer IN = 0;
	public static final Integer OUT = 1;
	
	public ProcedureParams(Object value, Class<?> clazz, int parameterOrder) {
		init( value, clazz, parameterOrder, null);
	}
	
	public ProcedureParams(Object value, Class<?> clazz, int parameterOrder, String varNameOut) {
		init( value, clazz, parameterOrder, varNameOut);
	}
	
	public void init(Object value, Class<?> clazz, int parameterOrder, String varNameOut) {
		this.setValue(value);
		this.setClazz(clazz);
		this.setParameterOrder(parameterOrder);
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
	
	
}
