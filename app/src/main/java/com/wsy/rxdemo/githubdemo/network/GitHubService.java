package com.wsy.rxdemo.githubdemo.network;

import com.wsy.rxdemo.githubdemo.adapter.UserListAdapter;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description
 * 2016/5/24.
 */
public interface GitHubService {
    String ENDPOINT = "https://api.github.com";

    // 获取个人信息
    @GET("/users/{user}")
    Observable<UserListAdapter.GitHubUser> getUserData(@Path("user") String user);
}
