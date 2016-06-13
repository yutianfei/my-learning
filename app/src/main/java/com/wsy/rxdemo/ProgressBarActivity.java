package com.wsy.rxdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wsy.rxdemo.viewwrapper.ViewWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Description 进度条显示
 * 2016/6/7.
 */
public class ProgressBarActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.view_gray)
    View mViewGray;

    @BindView(R.id.view_gradient)
    View mViewGradient;

    int count = 1;
    int total = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void showGradient(View view) {
        ViewGroup.LayoutParams params = mViewGradient.getLayoutParams();
        params.width = mViewGray.getWidth() / 3;
        mViewGradient.setLayoutParams(params);
    }

    @OnClick(R.id.btn_click2)
    public void showGradient2(View view) {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        mViewGradient.startAnimation(animation);
    }

    @OnClick(R.id.btn_click3)
    public void showGradient3(View view) {
        Observable.create(new Observable.OnSubscribe<Float>() {
            @Override
            public void call(Subscriber<? super Float> subscriber) {
                count++;
                float fraction = (float) count / (float) total;
                subscriber.onNext(fraction);
                subscriber.onCompleted();
            }
        }).repeat(total)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Float>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Float fraction) {
                        ViewWrapper wrapper = new ViewWrapper(mViewGradient);
                        ObjectAnimator.ofInt(wrapper, "width", (int) (mViewGray.getWidth() * fraction)).setDuration(5000).start();
                    }
                });
    }

}