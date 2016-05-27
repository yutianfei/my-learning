package com.wsy.rxdemo.cachedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wsy.rxdemo.R;

import butterknife.ButterKnife;

/**
 * Description 缓存模式: 读取数据库, 显示, 请求数据, 存储到数据库, 再更新页面
 * 2016/5/25.
 */
public class RxCacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_cache);
        ButterKnife.bind(this);
    }
}
