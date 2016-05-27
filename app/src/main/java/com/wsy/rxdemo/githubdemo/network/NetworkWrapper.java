package com.wsy.rxdemo.githubdemo.network;

import com.wsy.rxdemo.githubdemo.adapter.UserListAdapter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description
 * 2016/5/24.
 */
public class NetworkWrapper {

    private static final String[] mFamousUsers =
            {"SpikeKing", "JakeWharton", "rock3r", "Takhion", "dextorer", "Mariuxtheone", "yutianfei"};

    // 获取用户信息
    public static void getUsersInfo(final UserListAdapter adapter) {
        final GitHubService gitHubService =
                ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);

        Observable.from(mFamousUsers)
                .flatMap(new Func1<String, Observable<UserListAdapter.GitHubUser>>() {
                    @Override
                    public Observable<UserListAdapter.GitHubUser> call(String s) {
                        return gitHubService.getUserData(s);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserListAdapter.GitHubUser>() {
                    @Override
                    public void call(UserListAdapter.GitHubUser gitHubUser) {
                        adapter.addUser(gitHubUser);
                    }
                });
    }

}
