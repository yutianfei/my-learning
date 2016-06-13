package com.wsy.rxdemo.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;

import com.wsy.rxdemo.mvpdemo.model.IUser;
import com.wsy.rxdemo.mvpdemo.model.UserModel;
import com.wsy.rxdemo.mvpdemo.view.ILoginView;

/**
 * Description
 * 2016/5/30.
 */
public class LoginPresenterCompl implements ILoginPresenter {

    private ILoginView mLoginView;
    private IUser mUser;
    private Handler mHandler;

    public LoginPresenterCompl(ILoginView mLoginView) {
        this.mLoginView = mLoginView;
        mHandler = new Handler(Looper.getMainLooper());
        initUser();
    }

    private void initUser() {
        mUser = new UserModel("wsy", "111111");
    }

    @Override
    public void clear() {
        mLoginView.onClearText();
    }

    @Override
    public void login(String username, String password) {
        boolean result = false;
        final int code = mUser.checkUserValidity(username, password);
        if (code == 0) {
            result = true;
        }
        final boolean loginResult = result;
        mHandler.postDelayed(() -> mLoginView.onLoginResult(loginResult, code), 1000);
    }
}
