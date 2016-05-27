package com.wsy.rxdemo.cachedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wsy.rxdemo.R;

import butterknife.ButterKnife;

/**
 * Description
 * 2016/5/25.
 */
public class RxCachedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        ButterKnife.bind(this);
    }
}
