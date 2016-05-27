package com.wsy.rxdemo.backdemo;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

/**
 * Description
 * 2016/5/25.
 */
public class CustomAsyncTask extends AsyncTask<Void, Integer, Void> {

    private WeakReference<BackgroundActivity> mActivity; // 弱引用Activity, 防止内存泄露
    private boolean mCompleted = false; // 是否完成

    // 设置Activity控制ProgressBar
    public void setActivity(BackgroundActivity activity) {
        mActivity = new WeakReference<BackgroundActivity>(activity);
    }

    // 判断是否完成
    public boolean isCompleted() {
        return mCompleted;
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 1; i < BackgroundActivity.MAX_PROGRESS + 1; i++) {
            SystemClock.sleep(BackgroundActivity.EMIT_DELAY_MS);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mActivity.get().setProgressValue(values[0]); // 更新ProgressBar的值
        mActivity.get().setProgressPercentText(values[0]); // 设置文字
    }

    @Override
    protected void onPreExecute() {
        mActivity.get().setProgressText("开始异步任务..."); // 准备开始
        mCompleted = false;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mCompleted = true;
        mActivity.get().setBusy(false);
        mActivity.get().setProgressValue(0);
    }
}
