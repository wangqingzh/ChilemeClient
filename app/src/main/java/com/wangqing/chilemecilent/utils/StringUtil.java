package com.wangqing.chilemecilent.utils;

public class StringUtil {
    /**
     * 省略字符串
     * @param rawString
     * @param maxLine
     * @return
     */
    public static String omitString(String rawString, int maxLine){
        if (rawString.length() > maxLine){
            return rawString.substring(0, maxLine) + "...";
        }
        return rawString;
    }
}
