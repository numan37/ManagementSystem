package com.transaction.management.commons;

public class CommonHelperClass {
	
	public static boolean isNotNullAndEmpty(String value) {

		if (null == value) {
			return false;
		}
		value = value.trim();
		return value.length() >= 1 && !"null".equalsIgnoreCase(value);

	}

}
