package com.wsy.rxdemo.moviedemo.entity;

import lombok.Data;

/**
 * Description
 * 2016/5/25.
 */
@Data
public class Rating {
    private int max;
    private double average;
    private String stars;
    private int min;
}
