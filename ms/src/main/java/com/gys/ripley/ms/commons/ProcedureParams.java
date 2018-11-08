package com.gys.ripley.ms.commons;

import javax.persistence.ParameterMode;

public class ProcedureParams {
	
	private Object value;
	private ParameterMode paramMode;
	private Class<?> clazz;
	private int parameterOrder;
	
	public ProcedureParams(Object value, ParameterMode paramMode, Class<?> clazz, int parameterOrder) {
		this.setValue(value);
		this.setParamMode(paramMode);
		this.setClazz(clazz);
		this.setParameterOrder(parameterOrder);
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ParameterMode getParamMode() {
		return paramMode;
	}

	public void setParamMode(ParameterMode paramMode) {
		this.paramMode = paramMode;
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
	
	
}
