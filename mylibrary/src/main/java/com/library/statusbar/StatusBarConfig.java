package com.library.statusbar;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Description 设置状态栏颜色
 * 2016/5/17.
 */
public class StatusBarConfig {

    /**
     * 设置状态栏颜色
     *
     * @param colorResId 颜色资源id
     */
    public static void setTranslucentColor(Window window, int colorResId) {
        if (window == null) return;
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslucentStatusBarLollipop(window, colorResId);
        } else if (sdkInt >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatusBarKiKat(window);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void setTranslucentStatusBarLollipop(Window window, int colorResId) {
        window.setStatusBarColor(
                window.getContext()
                        .getResources()
                        .getColor(colorResId));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void setTranslucentStatusBarKiKat(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

}
