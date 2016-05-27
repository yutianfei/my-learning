package com.wsy.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * 2016/5/24.
 */
public class SimpleActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);

        Observable<String> observable = Observable.create(mObservableAction);
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(mTextSubscriber);
        observable.subscribe(mToastSubscriber);
    }

    private Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext(sayMyName());
        }
    };

    // 创建字符串
    private String sayMyName() {
        return "Hello, I am your friend, Spike!";
    }

    // 订阅者, 接收字符串, 修改控件
    private Subscriber<String> mTextSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(String s) {
            mTvText.setText(s); // 设置文字
        }
    };

    // 订阅者, 接收字符串, 提示信息
    private Subscriber<String> mToastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(String s) {
            Toast.makeText(SimpleActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };

}
