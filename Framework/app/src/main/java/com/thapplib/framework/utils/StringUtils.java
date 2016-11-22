package com.thapplib.framework.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by sll on 2016/11/18.
 * String工具类
 */

public class StringUtils {
    /**
     * 字符串分隔
     * @param text 字符串
     * @param regularExpression 分隔符
     * @return
     */
    public static String[] split(String text,String regularExpression)
    {
        String[] strings = null;
        if(!TextUtils.isEmpty(text))
        {
            strings = text.split(regularExpression);
        }
        return  strings;
    }

    public static String formart(Context context, int formatResId, Object... args){
        if (args.length == 0 || args[0] == null) return null;
        String result = null;
        String format = context.getString(formatResId);
        if (format!=null){
            result = String.format(format, args);
        }
        return result;
    }

    /**
     * 格式字符串
     * @param formart 要被格式化的string
     * @param args 第几个插入的变量
     * @return
     */
    public static String formart(String formart, Object... args){
        if (args.length == 0 || args[0] == null) return formart;
        return String.format(formart, args);
    }
}
