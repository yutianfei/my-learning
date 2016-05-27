package com.wsy.rxdemo.moviedemo.entity;

import java.util.List;

import lombok.Data;

/**
 * Description
 * 2016/5/25.
 */
@Data
public class Subjects {
    private Rating rating;
    private List<String> genres;
    private String title;
    private List<Casts> casts;
    private String collect_count;
    private String original_title;
    private String subtype;
    private List<Directors> directors;
    private String year;
    private Images images;
    private String alt;
    private String id;
}
