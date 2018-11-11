package com.gys.ripley.commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class FunctionsUtil {
	
	public static final char[] CHARS_NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static String leftPad(String text, int length, String repeatContent) {
		return StringUtils.leftPad(text, length, repeatContent);
	}
	
	public static String rightPad(String text, int length, String repeatContent) {
		return StringUtils.rightPad(text, length, repeatContent);
	}
	
	public static String replaceTags(String text, HashMap<String, Object> map) {
		if(map==null) {
			return text;
		}
		
		for(String key: map.keySet()) {
			Object obj = map.get(key);
			text = replaceText(text, key, obj.toString());
		}
		
		return text;
	}
	
	public static String replaceText(String text, String textFind, String textReplaced) {
		return text.replaceAll(textFind, textReplaced);
	}
	
	
	public static String join(Object... values) {
		if (values == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		for (Object obj : values) {
			sb.append(obj.toString());
		}

		return sb.toString();
	}

	public static boolean equiv(Object object1, Object object2) {
		if (isEmpty(object1) && !isEmpty(object2) || !isEmpty(object1) && isEmpty(object2)) {
			return false;
		}
		if (isEmpty(object1) && isEmpty(object2) || object1 == object2) {
			return true;
		}

		if (object1 instanceof String && object2 instanceof String) {
			return toBlank(object1.toString()).equals(toBlank(object2.toString()));
		}
		return object1.equals(object2);
	}

	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			return object.toString().trim().length() == 0;
		}
		if (object instanceof StringBuilder) {
			return object.toString().trim().length() == 0;
		}
		if (object instanceof List<?> || object instanceof ArrayList<?>) {
			return ((List<?>) object).isEmpty();
		}
		if (object instanceof Map<?, ?> || object instanceof HashMap<?, ?>) {
			return ((Map<?, ?>) object).isEmpty();
		}
		return false;
	}

	public static String toBlank(String cadena) {
		return isEmpty(cadena) ? "" : cadena;
	}

	public static final String createRandomString(int longitud, char[] baseChars) {
		int totalChars = baseChars.length - 1;

		String randomString = "";

		for (int i = 0; i < longitud; i++) {
			int randomIndex = Integer.parseInt(Math.round(Math.random() * totalChars) + "");
			randomString = randomString.concat(baseChars[randomIndex] + "");
		}

		return randomString;
	}
	
	/**
     * Metodo convierte una cadena a BigDecimal.
     *
     *
     * @param objeto
     * @return
     */
    public static Date toDate(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return (Date) (objeto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a long.
     *
     *
     * @param cadena
     * @return
     */
    public static Long toLong(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Long.parseLong(objeto.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a short.
     *
     * @param cadena
     * @return
     */
    public static Short toShort(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Short.parseShort(objeto.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a integer.
     *
     * @param cadena
     * @return
     */
    public static Integer toInteger(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Integer.parseInt(objeto.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a double.
     *
     * @param cadena
     * @return
     */
    public static Double toDouble(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Double.parseDouble(objeto.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a float.
     *
     *
     * @param cadena
     * @return
     */
    public static Float toFloat(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Float.parseFloat(objeto.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    /**
     * Metodo convierte un objeto a cadena
     *
     * @param cadena
     * @return
     */
    public static String toStr(Object cadena) {
        return isEmpty(cadena) ? null : toBlank(cadena.toString());
    }

    /**
     * Metodo devuelve una cadena, cadena vacia si el objeto es null (uso para grilla).
     *
     * @param object
     * @return
     */
    public static String toBlankObject(Object object) {
        return isEmpty(object) ? "" : object.toString();
    }
    
    public static boolean inList(String cadena, String... valores) {
        for (String valor : valores) {
            if (cadena.equals(valor)) {
                return true;
            }
        }
        return false;
    }
	
}
