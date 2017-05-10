package com.huans.commons.email.utils;

import java.util.Collection;
import java.util.List;

public class CollectionUtils {
	
    public static boolean isEmpty(Collection<?> coll) {
        return (coll == null || coll.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !CollectionUtils.isEmpty(coll);
    }
	
    public static void assertEmpty(List<?> coll, String paramName) throws IllegalArgumentException{
		if(isEmpty(coll)) {
			throw new IllegalArgumentException(paramName + " is empty");
		}
	}
}
