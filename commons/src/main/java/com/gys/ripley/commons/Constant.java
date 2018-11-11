package com.gys.ripley.commons;


public class Constant {
	
	private Constant() {
		throw new IllegalStateException( ErrorMessages.CONSTANT_CLASS_NOT_INSTANCE.generateMessage("Constant") );
	}
	
	public static Integer NO_ERROR = 0;
	public static Integer HAS_ERROR = 1;
	
}
