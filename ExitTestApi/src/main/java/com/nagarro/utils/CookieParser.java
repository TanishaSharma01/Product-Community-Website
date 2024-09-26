package com.nagarro.utils;

import java.util.HashMap;
import java.util.Map;

public class CookieParser {

    public static Map<String, String> parseString(String inputString) {
        Map<String, String> keyValueMap = new HashMap<>();
        String[] parts = inputString.split("_");
        if (parts.length == 2) {
            keyValueMap.put("id", parts[0]);
            keyValueMap.put("email", parts[1]);
        }
        return keyValueMap;
    }
    
    public static String createCookieValue(int id, String email) {
    	String str = id + "_" + "email";
    	return str;
    }
}
