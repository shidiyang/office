package com.lu.office.service.utile;

/**
 * Created by user on 5/24/17.
 */
public class DataUtil {

    public static String objToStr(String str) {
        if(str == null || "".equals(str)){
            return "";
        }else{
            return str.trim();
        }
    }
}
