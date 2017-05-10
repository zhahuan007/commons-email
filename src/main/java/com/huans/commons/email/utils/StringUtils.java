package com.huans.commons.email.utils;

import java.util.List;

public class StringUtils {
	
	public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
	
	public static void assertBlank(String str, String paramName) throws IllegalArgumentException{
		if(isBlank(str)) {
			throw new IllegalArgumentException(paramName + " is blank");
		}
	}
	
	
	public static String[] toArray(List<String> list) {
		return list.toArray(new String[]{});
	}
	
}
