package com.wsy.rxdemo.moviedemo.entity;

import java.util.List;

import lombok.Data;

/**
 * Description
 * 2016/5/25.
 */
@Data
public class MovieEntity {
    private int count;
    private int start;
    private int total;
    private List<Subjects> subjects;
    private String title;
}
