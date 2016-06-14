package com.wsy.rxdemo.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wsy.rxdemo.R;
import com.wsy.rxdemo.databinding.ActivityDataIncludeBinding;

/**
 * Description
 * 2016/5/30.
 */
public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_databinding);
        User user = new User("王", "书莹");
        binding.setUser(user);
        binding.setHandler(new MyClickHandlers());
        binding.setNote("note");
        binding.setImage(getDrawable(R.drawable.img));
        binding.setNum(100);

//        ActivityDataIncludeBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_data_include);
//        User user = new User("王", "书莹");
//        binding.setUser(user);
//        binding.setHandler(new MyClickHandlers());
//        binding.setNote("note");
//        binding.setImage(getDrawable(R.drawable.img));
//        binding.setNum(100);
    }
}
