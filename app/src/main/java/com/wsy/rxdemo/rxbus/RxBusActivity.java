package com.wsy.rxdemo.rxbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.wsy.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Description
 * 2016/5/27.
 */
public class RxBusActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    private Subscription rxSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        ButterKnife.bind(this);

        rxSubscription = RxBus.getDefault().toObserverable(UserEvent.class)
                .subscribe(userEvent -> tvText.setText(userEvent.getName()),
                        throwable -> tvText.setText(throwable.getMessage()));

        RxBus.getDefault().post("lam");
        RxBus.getDefault().post(new UserEvent(1, "lam"));

    }

    @OnClick(R.id.btn_rxbus)
    public void click() {
        startActivity(new Intent(this, RxBusNextActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName(), "OnDestroy");
        if (!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }
}
