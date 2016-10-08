package com.framework.util;

import java.util.Arrays;

public class StringUtil {

	public static boolean isNull(Object activityId) {
		return activityId == null;
	}

	public static boolean isNotNull(Object activityId) {
		return activityId != null;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String[] toUpperCase(String[] strings) {
		String[] upperStrings = null;
		String upperString = Arrays.toString(strings).toUpperCase();

		upperStrings = upperString.substring(1, upperString.length() - 1)
				.split(", ");

		return upperStrings;
	}

}
