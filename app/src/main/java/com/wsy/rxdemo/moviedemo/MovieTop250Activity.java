package com.wsy.rxdemo.moviedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.wsy.rxdemo.R;
import com.wsy.rxdemo.moviedemo.entity.Subjects;
import com.wsy.rxdemo.moviedemo.network.HttpMethods;
import com.wsy.rxdemo.moviedemo.subscribe.ProgressSubscriber;
import com.wsy.rxdemo.moviedemo.subscribe.SubscriberOnNextListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subjects.Subject;

/**
 * Description
 * 2016/5/25.
 */
public class MovieTop250Activity extends AppCompatActivity {

    @BindView(R.id.click_me_BN)
    Button clickMeBN;
    @BindView(R.id.result_TV)
    TextView resultTV;

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_top);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    //进行网络请求
    private void getMovie() {
        HttpMethods.getInstance().getTopMovie(
                new ProgressSubscriber<List<Subjects>>(getTopMovieOnNext, this),
                0, 10);
    }
}
