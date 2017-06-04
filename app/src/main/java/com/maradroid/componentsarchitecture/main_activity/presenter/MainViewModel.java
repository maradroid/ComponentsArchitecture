package com.maradroid.componentsarchitecture.main_activity.presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.maradroid.componentsarchitecture.base.MyApp;
import com.maradroid.componentsarchitecture.main_activity.interactor.MainInteractor;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by mara on 6/3/17.
 */

//public class MainViewModel extends ViewModel {
public class MainViewModel {
    private MutableLiveData<String> timeData = new MutableLiveData<>();
    private MutableLiveData<String> errorData = new MutableLiveData<>();

    MainInteractor interactor;

    public MainViewModel(MainInteractor interactor) {
        this.interactor = interactor;
    }

    /*@Override
    protected void onCleared() {
        super.onCleared();
        Log.e("maradroid", "MainViewModel onCleared");
    }*/

    public LiveData<String> getTimeData() {
        return timeData;
    }

    public LiveData<String> getErrorData() {
        return errorData;
    }

    public void startCounting() {
        Log.e("maradroid", "MainViewModel: startCounting");
        interactor.getTime(provideObserver());
    }

    private DisposableObserver<String> provideObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                timeData.setValue(s);
            }

            @Override
            public void onError(Throwable e) {
                errorData.setValue(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    //LiveDataReactiveStreams
}
