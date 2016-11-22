package com.thapplib.framework.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by SLL on 2016/11/18.
 * 图片工具类
 */

public class ImageViewUtils {
    /**
     * 设置显示图片
     *
     * @param uri
     * @param imageView
     * @param options
     */
    public static void displayImage(String uri, ImageView imageView, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(uri, imageView, options);
    }

    /**
     * 设置显示图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayImage(String uri, ImageView imageView) {
        displayImage(uri, imageView,null);
    }
}
