package com.sobhan.mykuya.network;

import com.sobhan.mykuya.model.Result;

import java.util.List;

import io.reactivex.Observable;

import io.reactivex.disposables.Disposable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("product/catalog")
    Observable<List<Result>> getResult(@Header("Authorization") String token, @Query("lat") String lat,
                                       @Query("lng") String lng);
}
