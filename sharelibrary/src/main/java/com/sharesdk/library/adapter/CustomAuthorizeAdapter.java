package com.sharesdk.library.adapter;

import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * 修改网页授权页面
 */
public class CustomAuthorizeAdapter extends AuthorizeAdapter {

    @Override
    public void onCreate() {
        // 隐藏标题栏Powered by ShareSDK
        hideShareSDKLogo();
    }


}
