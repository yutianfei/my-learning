package com.wsy.rxdemo.moviedemo.network;

import lombok.Data;

/**
 * Description
 * 2016/5/25.
 */
@Data
public class HttpResult<T> {
    //用来模仿resultCode和resultMessage
    private int count;
    private int start;
    private int total;
    private String title;

    //用来模仿Data
    private T subjects;
}
