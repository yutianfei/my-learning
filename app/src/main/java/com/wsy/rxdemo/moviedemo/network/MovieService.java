package com.wsy.rxdemo.moviedemo.network;

import com.wsy.rxdemo.moviedemo.entity.Subjects;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description
 * 2016/5/25.
 */
public interface MovieService {

    //    @GET("top250")
    //    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    //    @GET("top250")
    //    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);


    @GET("top250")
    Observable<HttpResult<List<Subjects>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
