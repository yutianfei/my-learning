package com.wsy.rxdemo.mvpdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wsy.rxdemo.R;
import com.wsy.rxdemo.mvpdemo.presenter.ILoginPresenter;
import com.wsy.rxdemo.mvpdemo.presenter.LoginPresenterCompl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description
 * 2016/5/30.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_clear)
    Button btnClear;

    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterCompl(this);
    }

    @OnClick(R.id.btn_login)
    void click() {
        btnClear.setEnabled(false);
        btnLogin.setEnabled(false);
        loginPresenter.login(editUsername.getText().toString(), editPwd.getText().toString());
    }

    @OnClick(R.id.btn_clear)
    void clear() {
        loginPresenter.clear();
    }

    @Override
    public void onClearText() {
        editUsername.setText("");
        editPwd.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        btnClear.setEnabled(true);
        btnLogin.setEnabled(true);
        if (result) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Fail, code = " + code, Toast.LENGTH_SHORT).show();
        }
    }
}
