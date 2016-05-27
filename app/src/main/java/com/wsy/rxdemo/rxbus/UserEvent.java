package com.wsy.rxdemo.rxbus;

import lombok.Data;

/**
 * Description
 * 2016/5/27.
 */
@Data
public class UserEvent {
    private int id;
    private String name;

    public UserEvent() {
    }

    public UserEvent(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
