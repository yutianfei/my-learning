package com.wsy.rxdemo.mvpdemo.model;

import lombok.Data;

/**
 * Description
 * 2016/5/30.
 */
@Data
public class UserModel implements IUser {

    String username;
    String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int checkUserValidity(String name, String pwd) {
        if (name == null || pwd == null || !name.equals(username) || !pwd.equals(password)) {
            return -1;
        }
        return 0;
    }
}
