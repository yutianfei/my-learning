package com.wsy.rxdemo.sms;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    public static final int MY_PERMISSIONS_REQUEST_RECEIVER_SMS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 23) {
            // 检查短信权限
            checkPermission();
        }

        // 使用广播监听
        //registerSmsReceiver();

        // 使用ContentObserver注册短信变化监听
        SmsContent content = new SmsContent(this, new Handler(), editCode);
        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            new AlertDialog.Builder(this)
                    .setMessage("通过此权限后将自动将填充获取的验证码")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 申请授权
                            ActivityCompat.requestPermissions(SmsActivity.this, new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                                    MY_PERMISSIONS_REQUEST_RECEIVER_SMS);
                        }
                    }).create().show();
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)
//                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
//                // Show an expanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission
//
//            } else {
//                // 未授权，则申请授权
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
//                        MY_PERMISSIONS_REQUEST_RECEIVER_SMS);
//            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECEIVER_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SmsActivity.this, "通过授权", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SmsActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterSmsReceiver();
    }

    private void registerSmsReceiver() {
        smsReceiver = new MySmsReceiver(this);
        IntentFilter intentFilter = new IntentFilter(MySmsReceiver.SMS_RECEIVED_ACTION);
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
