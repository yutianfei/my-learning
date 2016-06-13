package com.wsy.rxdemo.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wsy.rxdemo.R;

/**
 * Description
 * 2016/5/30.
 */
public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.wsy.rxdemo.databinding.ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        User user = new User("fei", "Liang");
        binding.setUser(user);
    }
}
