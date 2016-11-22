package com.thapplib.framework.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sll on 2016/11/16.
 * Toast 提醒工具类
 */

public class T {
    /**
     * 不给外面实例化这个类，实例化就会报这个异常
     */
    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 是否需要toast提醒，可以在application的onCreate函数里面初始化
    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context 上下文环境
     * @param message  字符序列（先理解为文本字符等）
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文环境
     * @param message  int值类型的
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文环境
     * @param message  字符序列（先理解为文本字符等）
     */
    public static void showLong(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文环境
     * @param message  int值类型的
     */
    public static void showLong(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文环境
     * @param message  字符序列（先理解为文本字符等）
     * @param duration  自定义toast显示时长
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文环境
     * @param message  int值类型的
     * @param duration  自定义toast显示时长
     */
    public static void show(Context context, int message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

}
