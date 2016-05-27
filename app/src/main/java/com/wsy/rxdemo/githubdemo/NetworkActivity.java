package com.wsy.rxdemo.githubdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wsy.rxdemo.R;
import com.wsy.rxdemo.githubdemo.adapter.UserListAdapter;
import com.wsy.rxdemo.githubdemo.network.NetworkWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description
 * 2016/5/24.
 */
public class NetworkActivity extends AppCompatActivity {

    @BindView(R.id.network_rv_list)
    RecyclerView mRvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);

        // 设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);

        // 设置适配器
        UserListAdapter adapter = new UserListAdapter();
        NetworkWrapper.getUsersInfo(adapter);
        mRvList.setAdapter(adapter);
    }

}
