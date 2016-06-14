package com.wsy.rxdemo.sms;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.wsy.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description
 * 2016/6/14.
 */
public class SmsActivity extends AppCompatActivity implements MySmsReceiver.GetMsgListener {

    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;

    private MySmsReceiver smsReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSmsReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterSmsReceiver();
    }

    private void registerSmsReceiver() {
        smsReceiver = new MySmsReceiver(this);
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(1000);
        registerReceiver(smsReceiver, intentFilter);
    }

    private void unregisterSmsReceiver() {
        unregisterReceiver(smsReceiver);
    }

    @OnClick(R.id.btn_get_code)
    public void getCode() {
        btnGetCode.setEnabled(false);
    }

    @Override
    public void showMsg(String code) {
        editCode.setText(code);
        btnGetCode.setEnabled(true);
        btnGetCode.setText("下一步");
    }
}
