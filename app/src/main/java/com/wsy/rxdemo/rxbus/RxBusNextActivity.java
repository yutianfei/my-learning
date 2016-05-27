package com.wsy.rxdemo.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.wsy.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Description
 * 2016/5/27.
 */
public class RxBusNextActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.btn_rxbus)
    Button btn;

    private Subscription rxSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        ButterKnife.bind(this);

        rxSubscription = RxView.clicks(btn).subscribe(aVoid -> RxBus.getDefault().post(new UserEvent(1, "wsy")));

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
