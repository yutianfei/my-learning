package com.wsy.rxdemo.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MySmsReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private GetMsgListener listener;

    public MySmsReceiver(GetMsgListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                //将pdus里面的内容转化成Object[]数组
                Object pdusData[] = (Object[]) bundle.get("pdus"); // pdus ：protocol data unit
                SmsMessage[] msg = new SmsMessage[pdusData.length]; //解析短信
                for (int i = 0; i < msg.length; i++) {
                    byte pdus[] = (byte[]) pdusData[i];
                    msg[i] = SmsMessage.createFromPdu(pdus);
                }
                StringBuffer content = new StringBuffer();//获取短信内容
                StringBuffer phoneNumber = new StringBuffer();//获取地址
                //分析短信具体参数
                for (SmsMessage temp : msg) {
                    content.append(temp.getMessageBody());
                    phoneNumber.append(temp.getOriginatingAddress());

                    // 将信息传递给activity
                    if(temp.getOriginatingAddress().equals("10086")){
                        listener.showMsg(temp.getMessageBody());
                    }
                }
                System.out.println("发送者号码：" + phoneNumber.toString() + "  短信内容：" + content.toString());
            }
        }
    }

    public interface GetMsgListener {
        void showMsg(String code);
    }
}
