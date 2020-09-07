package com.sobhan.mykuya.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sobhan.mykuya.model.Product;
import com.sobhan.mykuya.model.Result;
import com.sobhan.mykuya.network.ApiClient;
import com.sobhan.mykuya.network.ApiInterface;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ResultRepository {
    private ApiInterface apiInterface;

    public ResultRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public LiveData<List<Product>> getResultData(String token, String lat, String lng) {
        final MutableLiveData<List<Product>> data = new MutableLiveData<>();
        apiInterface.getResult(token, lat, lng).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Result>>() {
                    @Override
                    public void onNext(List<Result> results) {
                        data.setValue(results.get(0).getProducts());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return data;
    }

    public LiveData<List<Product>> getAllResult(String token, String lat, String lng) {
        final MutableLiveData<List<Product>> data = new MutableLiveData<>();


        apiInterface.getResult(token, lat, lng).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Result>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Result> value) {
                        data.setValue(value.get(1).getProducts());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return data;
    }

}
