package com.gys.ripley.commons;

import java.lang.reflect.Field;

import com.gys.ripley.commons.exception.CommonsException;

import static com.gys.ripley.commons.FunctionsUtil.*;

public class ReflectionUtil {

	public void setValueInField(Object obj, String varName, Object value) throws CommonsException {

		if( isEmpty(value) ) {
			return;
		}
		
		try {

			Field field = getFieldFromObject(obj, varName);
			boolean accesible = field.isAccessible();

			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(accesible);

		} catch (SecurityException e) {
			throw new CommonsException(ErrorMessages.FIELD_NOT_PERMISSION);
		} catch (IllegalArgumentException e) {
			throw new CommonsException(ErrorMessages.FIELD_ILLEGAL_ARGUMENT);
		} catch (IllegalAccessException e) {
			throw new CommonsException(ErrorMessages.FIELD_ILLEGAL_ACCESS);
		}

	}

	private Field getFieldFromObject(Object obj, String varName) throws CommonsException{
		
		Class<?> current = obj.getClass();
		
		do {
			try {
				return current.getDeclaredField(varName);
			} catch (Exception e) { }
		} while ((current = current.getSuperclass()) != null);
		
		throw new CommonsException( ErrorMessages.FIELD_NOT_FOUND);
	}

}
