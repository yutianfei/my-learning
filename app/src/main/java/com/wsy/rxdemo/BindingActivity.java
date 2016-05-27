package com.wsy.rxdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.design.widget.RxSnackbar;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class BindingActivity extends AppCompatActivity {

    // Toolbar使用RxToolbar监听点击事件; Snackbar使用RxSnackbar监听;
    // EditText使用RxTextView监听; 其余使用RxView监听.

    @BindView(R.id.rxbinding_t_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rxbinding_et_usual_approach)
    EditText mEtUsualApproach;
    @BindView(R.id.rxbinding_et_reactive_approach)
    EditText mEtReactiveApproach;
    @BindView(R.id.rxbinding_tv_show)
    TextView mTvShow;
    @BindView(R.id.rxbinding_fab_fab)
    FloatingActionButton mFabFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        ButterKnife.bind(this);

        initToolbar(); // 初始化Toolbar
        initFabButton(); // 初始化Fab按钮
        initEditText(); // 初始化编辑文本
    }

    private void initToolbar() {
        // 添加菜单按钮
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        // 添加浏览按钮
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RxToolbar.itemClicks(mToolbar).subscribe(new Action1<MenuItem>() {
            @Override
            public void call(MenuItem menuItem) {
                String m = "点击\"" + menuItem.getTitle() + "\"";
                Toast.makeText(BindingActivity.this, m, Toast.LENGTH_SHORT).show();
            }
        });

        RxToolbar.navigationClicks(mToolbar).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(BindingActivity.this, "浏览点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFabButton() {
        RxView.clicks(mFabFab).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "点击Snackbar", Snackbar.LENGTH_SHORT);
                snackbar.show();
                RxSnackbar.dismisses(snackbar).subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        String text = "Snackbar消失代码:" + integer;
                        Toast.makeText(BindingActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initEditText() {
        // 正常方式
        mEtUsualApproach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvShow.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Rx方式
        RxTextView.textChanges(mEtReactiveApproach).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                mTvShow.setText(charSequence);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rxbinding, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
