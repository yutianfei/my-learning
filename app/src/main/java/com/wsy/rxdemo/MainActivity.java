package com.wsy.rxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wsy.rxdemo.backdemo.BackgroundActivity;
import com.wsy.rxdemo.cachedemo.RxCacheActivity;
import com.wsy.rxdemo.githubdemo.NetworkActivity;
import com.wsy.rxdemo.lambda.LambdaActivity;
import com.wsy.rxdemo.moviedemo.MovieTop250Activity;
import com.wsy.rxdemo.rxbus.RxBusActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    // 跳转简单的页面
    @OnClick(R.id.btn_1)
    public void gotoSimpleModule(View view) {
        startActivity(new Intent(this, SimpleActivity.class));
    }

    // 跳转复杂的页面
    @OnClick(R.id.btn_2)
    public void gotoMoreModule(View view) {
        startActivity(new Intent(this, MoreActivity.class));
    }

    // 跳转网络的页面
    @OnClick(R.id.btn_3)
    public void gotoNetworkModule(View view) {
        startActivity(new Intent(this, NetworkActivity.class));
    }

    // 跳转线程安全的页面
    @OnClick(R.id.btn_4)
    public void gotoSafeModule(View view) {
        startActivity(new Intent(this, SafeActivity.class));
    }

    // RxBinding
    @OnClick(R.id.btn_5)
    public void gotoRxBinding(View view) {
        startActivity(new Intent(this, BindingActivity.class));
    }

    // 在页面切换时持续更新控件
    @OnClick(R.id.btn_6)
    public void gotoBackground(View view) {
        startActivity(new Intent(this, BackgroundActivity.class));
    }

    // 使用Rx同步并缓存网络数据
    @OnClick(R.id.btn_7)
    public void gotoCache(View view) {
        startActivity(new Intent(this, RxCacheActivity.class));
    }

    // RxJava 与 Retrofit 结合的最佳实践(豆瓣电影)
    @OnClick(R.id.btn_8)
    public void gotoTop250(View view) {
        startActivity(new Intent(this, MovieTop250Activity.class));
    }

    // Lambda表达式
    @OnClick(R.id.btn_9)
    public void gotoLambda(View view) {
        startActivity(new Intent(this, LambdaActivity.class));
    }

    // RxBus
    @OnClick(R.id.btn_10)
    public void gotoRxBus(View view) {
        startActivity(new Intent(this, RxBusActivity.class));
    }
}
