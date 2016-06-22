package com.sharesdk.library;

import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.wechat.favorite.WechatFavorite;

/**
 * 分享功能管理
 */
public class ShareManager {

    /**
     * 调用ShareSDK执行分享
     *
     * @param context
     * @param platformToShare 指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     * @param showContentEdit 是否显示编辑页
     */
    public static void showShare(Context context, String platformToShare, boolean showContentEdit) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 在自动授权时可以禁用SSO方式
        // oks.disableSSOWhenAuthorize();
        oks.setTitle(context.getString(R.string.share_sdk_title));
        oks.setTitleUrl(context.getString(R.string.share_sdk_title_url));
        oks.setText(context.getString(R.string.share_sdk_content));
        oks.setUrl(context.getString(R.string.share_sdk_content_url)); //微信不绕过审核分享链接
        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
        oks.setImageUrl(context.getString(R.string.share_sdk_image_url));
        //oks.setFilePath("/sdcard/test-pic.jpg");  //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        oks.setComment(context.getString(R.string.share_sdk_comment)); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite(context.getString(R.string.share_sdk_site));  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl(context.getString(R.string.share_sdk_site_url));//QZone分享参数
        oks.setVenueName(context.getString(R.string.share_sdk_venue_name));
        oks.setVenueDescription(context.getString(R.string.share_sdk_venue_description));
        // 将快捷分享的操作结果将通过OneKeyShareCallback回调
        //oks.setCallback(new OneKeyShareCallback());
        // 去自定义不同平台的字段内容
        //oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
        // 在九宫格设置自定义的图标
        // Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon4);
        // String label = "ShareSDK";
        // View.OnClickListener listener = new View.OnClickListener() {
        //     public void onClick(View v) {
        //     }
        // };
        // oks.setCustomerLogo(logo, label, listener);

        // 为EditPage设置一个背景的View
        // oks.setEditPageBackground(getPage());

        // 隐藏九宫格中的新浪微博
        oks.addHiddenPlatform(WechatFavorite.NAME);

        // String[] AVATARS = {
        // 		"http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
        // 		"http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg" };
        // oks.setImageArray(AVATARS);              //腾讯微博和twitter用此方法分享多张图片，其他平台不可以

        // 启动分享
        oks.show(context);
    }
}
