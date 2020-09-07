package com.sobhan.mykuya.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sobhan.mykuya.model.Product;
import com.sobhan.mykuya.model.Result;
import com.sobhan.mykuya.repository.ResultRepository;
import com.sobhan.mykuya.utils.Constant;

import java.util.List;


public class ResultViewModel extends ViewModel {
    private ResultRepository resultRepository;
    private LiveData<List<Product>> data;
    private LiveData<List<Product>> allData;
    Constant constant = new Constant();


    public ResultViewModel() {
        super();
        resultRepository = new ResultRepository();
        data = resultRepository.getResultData(Constant.TOKEN, constant.getLAT(), constant.getLNG());
        allData = resultRepository.getAllResult(Constant.TOKEN, constant.getLAT(), constant.getLNG());
    }


    public LiveData<List<Product>> getResultLiveData() {
        return data;
    }

    public LiveData<List<Product>> getAllResultLiveData() {
        return allData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
