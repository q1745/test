package com.example.test;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("/api/picture/index?page=20")
    Observable<MyFood> get();
}
