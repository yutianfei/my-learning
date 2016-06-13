package com.wsy.rxdemo.viewwrapper;

import android.view.View;

/**
 * Description 包装原始对象，间接为其提供get和set方法
 * 2016/6/7.
 */
public class ViewWrapper {

    private View mTarget;

    public ViewWrapper(View target) {
        this.mTarget = target;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }
}
