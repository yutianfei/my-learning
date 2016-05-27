package com.wsy.rxdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxTestActivity extends AppCompatActivity {

    @BindView(R.id.main_thread)
    Button mThreadButton;
    @BindView(R.id.main_async)
    Button mAsyncButton;
    @BindView(R.id.main_rx)
    Button mRxButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);
        ButterKnife.bind(this);
    }

    // 线程运行
    @OnClick(R.id.main_thread)
    public void mainThread() {
        mThreadButton.setEnabled(false);
        longRunningOperation();
        Snackbar.make(findViewById(android.R.id.content), longRunningOperation(), Snackbar.LENGTH_LONG).show();
        mThreadButton.setEnabled(true);
    }

    // 异步运行
    @OnClick(R.id.main_async)
    public void mainAsync() {
        mAsyncButton.setEnabled(false);
        new MyAsyncTasks().execute();
    }

    // 响应式运行
    @OnClick(R.id.main_rx)
    public void mainRx() {
        mRxButton.setEnabled(false);
        observable.subscribe(subscriber);
    }

    // 长时间运行的任务
    private String longRunningOperation() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            Log.e("DEBUG", e.toString());
        }
        return "Complete!";
    }

    // 异步线程
    private class MyAsyncTasks extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_LONG).show();
            mAsyncButton.setEnabled(true);
        }

        @Override
        protected String doInBackground(Void... params) {
            return longRunningOperation();
        }
    }

    // 使用IO线程处理, 主线程响应
    private Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext(longRunningOperation());
            subscriber.onCompleted();
        }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            mRxButton.setEnabled(true);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(String s) {
            Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber != null && subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
            subscriber = null;
        }
    }
}
