package com.wsy.rxdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.wsy.rxdemo.backdemo.BackgroundActivity;
import com.wsy.rxdemo.cachedemo.RxCacheActivity;
import com.wsy.rxdemo.databinding.DataBindingActivity;
import com.wsy.rxdemo.githubdemo.NetworkActivity;
import com.wsy.rxdemo.lambda.LambdaActivity;
import com.wsy.rxdemo.moviedemo.MovieTop250Activity;
import com.wsy.rxdemo.mvpdemo.view.LoginActivity;
import com.wsy.rxdemo.rxbus.RxBusActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //微信 appid appsecret
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //支付宝 appid
        PlatformConfig.setAlipay("2015111700822536");

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS};
            requestPermissions(mPermissionList, 100);
        }
    }

    // 跳转简单的页面
    @OnClick(R.id.btn_1)
    public void gotoSimpleModule(View view) {
        startActivity(new Intent(this, SimpleActivity.class));
    }

    // 跳转复杂的页面
    @OnClick(R.id.btn_2)
    public void gotoMoreModule(View view) {
        startActivity(new Intent(this, MoreActivity.class));
    }

    // 跳转网络的页面
    @OnClick(R.id.btn_3)
    public void gotoNetworkModule(View view) {
        startActivity(new Intent(this, NetworkActivity.class));
    }

    // 跳转线程安全的页面
    @OnClick(R.id.btn_4)
    public void gotoSafeModule(View view) {
        startActivity(new Intent(this, SafeActivity.class));
    }

    // RxBinding
    @OnClick(R.id.btn_5)
    public void gotoRxBinding(View view) {
        startActivity(new Intent(this, BindingActivity.class));
    }

    // 在页面切换时持续更新控件
    @OnClick(R.id.btn_6)
    public void gotoBackground(View view) {
        startActivity(new Intent(this, BackgroundActivity.class));
    }

    // 使用Rx同步并缓存网络数据
    @OnClick(R.id.btn_7)
    public void gotoCache(View view) {
        startActivity(new Intent(this, RxCacheActivity.class));
    }

    // RxJava 与 Retrofit 结合的最佳实践(豆瓣电影)
    @OnClick(R.id.btn_8)
    public void gotoTop250(View view) {
        startActivity(new Intent(this, MovieTop250Activity.class));
    }

    // Lambda表达式
    @OnClick(R.id.btn_9)
    public void gotoLambda(View view) {
        startActivity(new Intent(this, LambdaActivity.class));
    }

    // RxBus
    @OnClick(R.id.btn_10)
    public void gotoRxBus(View view) {
        startActivity(new Intent(this, RxBusActivity.class));
    }

    // Dagger2
    @OnClick(R.id.btn_11)
    public void gotoMVP(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    // Dagger2
    @OnClick(R.id.btn_12)
    public void gotoDatabinding(View view) {
        startActivity(new Intent(this, DataBindingActivity.class));
    }

    // ProgressBar
    @OnClick(R.id.btn_13)
    public void gotoProgressBar(View view) {
        startActivity(new Intent(this, ProgressBarActivity.class));
    }

    // 分享
    @OnClick(R.id.btn_14)
    public void share(View view) {
        UMImage image = new UMImage(MainActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
        new ShareAction(this).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE)
                .withText("来自友盟分享面板")
                .withMedia(image)
                .setCallback(umShareListener)
                .withTargetUrl("http://www.umeng.com")
                .open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(MainActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }
}
