package com.thapplib.framework.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by sll on 2016/11/17.
 * 屏幕读取类
 */

public class ScreenUtils {

    /* cannot be instantiated */
    private void ScreenUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置全屏
     *
     * @param activity
     * @return
     */
    public static void setFullScreen(Activity activity) {
        //隐藏标题栏
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = activity.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
    /**
     * 获得屏幕密度 <屏幕密度（0.75 / 1.0 / 1.5）>
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.density;
    }

    /**
     * 获得屏幕密度DPI <屏幕密度DPI（120 / 160 / 240）> 开发基准屏的DPI为160 以此计算字体缩放大小
     *
     * @param context
     * @return
     */
    public static int getScreenDensityDpi(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.densityDpi;
    }

    /**
     * 获得状态栏的高度
     * 这个还未理解
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context)
    {

        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     * 不理解
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity)
    {
        //当前屏幕
        View view = activity.getWindow().getDecorView();
        //设置绘画缓存
        view.setDrawingCacheEnabled(true);
        //设置绘画缓存
        view.buildDrawingCache();
        //绘画缓存赋值给位图bmp，不理解
        Bitmap bmp = view.getDrawingCache();

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        //创建一个有view宽高的位图bp
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);

        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * API19以上的的设备上透明化状态栏和导航栏
     */
    @TargetApi(19)
    public static void alphaStatusBarAndNavBar(Activity activity, int statusColor)
    {
        // 如果不希望 APP 的内容被上拉到状态列 (Status bar) 的话，要记得在界面 (Layout) XML 档中，最外面的那层，要再加上一个属性 fitsSystemWindows为true
        if (Build.VERSION.SDK_INT > 18)
        {
            Window window = activity.getWindow();
            // 状态栏透明
            window.setFlags(0x04000000, 0x04000000);

//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            // 透明导航栏
//            window.setFlags(0x08000000, 0x08000000);
            changeStatusNavBarColor(activity, statusColor);
        }
    }

    /**
     * 4.4以上可以自定义状态栏和导航栏颜色
     */
    private static void changeStatusNavBarColor(Activity activity, int statusColor)
    {
        View statusBarTintView = new View(activity);
        FrameLayout.LayoutParams statusParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getStatusHeight(activity));
        statusParams.gravity = Gravity.TOP;
        FrameLayout.LayoutParams navParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,  getActionBarHeight(activity));
        navParams.gravity = Gravity.BOTTOM;
        FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
        statusBarTintView.setLayoutParams(statusParams);
        statusBarTintView.setBackgroundColor(statusColor);
        decorView.addView(statusBarTintView);
    }


    /**
     * 标题栏高度
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context)
    {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, dm);
            }
        }
        else
        {
            tv.data = 48;
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, dm);
        }
        return actionBarHeight;
    }
}
