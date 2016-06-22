package com.ushare.library;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

/**
 * 友盟分享管理
 */
public class UmengShareManager {
    /**
     * 友盟分享平台设置
     */
    public static void setSharePlatform(Context context) {
        //微信 appid appsecret
        PlatformConfig.setWeixin(context.getString(R.string.wx_appid), context.getString(R.string.wx_appsecret));
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo(context.getString(R.string.sina_appid), context.getString(R.string.sina_appsecret));
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(context.getString(R.string.tencent_appid), context.getString(R.string.tencent_appkey));
    }

    private UMShareListener umShareListener;
    private Activity context;
    private Toast toast;

    public UmengShareManager(Activity context) {
        this.context = context;
        this.toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        this.umShareListener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
                Log.d("plat", "platform" + platform);
                if (platform.name().equals("WEIXIN_FAVORITE")) {
                    toast.setText("收藏成功");
                    toast.show();
                } else {
                    toast.setText("分享成功");
                    toast.show();
                }
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                toast.setText("分享失败");
                toast.show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                toast.setText("分享取消");
                toast.show();
            }
        };
    }

    /**
     * 分享功能
     */
    public void shareApp() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS};
            ActivityCompat.requestPermissions(context, mPermissionList, 100);
        }

        UMImage image = new UMImage(context, context.getString(R.string.share_umeng_image_url));
        new ShareAction(context).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.DOUBAN)
                .withText(context.getString(R.string.share_umeng_content))
                .withMedia(image)
                .setCallback(umShareListener)
                .withTargetUrl(context.getString(R.string.share_umeng_url))
                .open();
    }
}
