package com.example.dina_el_hakim.vodafonecircularviewtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class StringUtil {

    private static final String REGEX_NUMBER = "[0-9]*";

    @Deprecated
    public static boolean isNullOrBlank(String value) {
        return (value == null || value.trim().length() == 0);
    }

    @Deprecated
    public static boolean isNotNullOrBlank(String value) {
        return !isNullOrBlank(value);
    }

    public static boolean isNumber(String value) {
        return (isNullOrBlank(value)) ? false : value.matches(REGEX_NUMBER);
    }

    @Deprecated
    public static boolean isNumberWithLength(String value, int length) {
        if (isNumber(value)) {
            return (value.length() == length);
        }
        return false;
    }

    public static String convertInputStreamToString(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /*
    * convert string to hashmap with the key and the value has same values
    * string contains values separated by specific separator
    * want to search for a value after that in that string
    * */
    public static HashMap<String, String> convertStringToHashMapWithSeparator(String wholeString, String separator) {
        HashMap<String, String> map = new HashMap<>();
        //split string into string array with the separator
        if (wholeString != null && !wholeString.isEmpty()) {
            String[] stringList = wholeString.split(separator);
            if (stringList.length > 0) {
                for (String str : stringList) {
                    map.put(str, str);
                }
            }
        }
        return map;
    }

    //search for specific key in a hashMap
    public static boolean isExistInMapByKey(HashMap<String, String> map, String key) {
        return map.containsKey(key);
    }

    //search for secific value in a hashMap
    public static boolean isExistInMapByValue(HashMap<String, String> map, String value) {
        return map.containsValue(value);
    }

    //check if the string starts with in hashmap is a start of anther string
    public static boolean isStartsWith(HashMap<String, String> map, String str) {
        boolean isStartWith = false;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (str.startsWith(e.getValue())) {
                    isStartWith = true;
                }
            }
        }
        return isStartWith;
    }
}
