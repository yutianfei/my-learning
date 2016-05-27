package com.wsy.rxdemo;

import android.os.Bundle;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Description
 * 2016/5/24.
 */
public class SafeActivity extends RxAppCompatActivity {

    private static final String TAG = SafeActivity.class.getSimpleName();

    @BindView(R.id.tv_text)
    TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);

        // 当前组件生命周期结束时，自动取消对Observable订阅
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle()) // 管理生命周期, 防止内存泄露
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        // 指定在哪个生命周期方法调用时取消订阅
//        Observable.interval(1, TimeUnit.SECONDS)
//                .compose(bindUntilEvent(ActivityEvent.PAUSE))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }

    private Subscriber subscriber = new Subscriber<Long>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            showError(e);
        }

        @Override
        public void onNext(Long aLong) {
            showTime(aLong);
        }
    };

    private void showTime(Long time) {
        mTvText.setText(String.valueOf("时间计数: " + time));
    }

    private void showError(Throwable throwable) {
        mTvText.setText(throwable.getMessage());
    }

}
