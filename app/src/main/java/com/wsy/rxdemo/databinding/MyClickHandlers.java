package com.wsy.rxdemo.databinding;

import android.view.View;
import android.widget.Toast;

/**
 * Description
 * 2016/6/14.
 */
public class MyClickHandlers {

    public void onClickFriend(View view) {
        Toast.makeText(view.getContext(), "Friend!", Toast.LENGTH_SHORT).show();
    }

    public void onClickEnemy(View view) {
        Toast.makeText(view.getContext(), "Enemy!", Toast.LENGTH_SHORT).show();
    }
}
