package com.maradroid.componentsarchitecture.main_activity.interactor;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by mara on 6/3/17.
 */

public class MainInteractor implements LifecycleObserver {

    private CompositeDisposable compositeDisposable;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public MainInteractor(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    public void getTime(DisposableObserver<String> observer) {
        Log.e("maradroid", "MainInteractor: getTime");
        addObserver(Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Long aLong) throws Exception {
                        return Observable.just(sdf.format(System.currentTimeMillis()));
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer));
    }

    private void addObserver(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed())
            compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e("maradroid", "MainInteractor: onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e("maradroid", "MainInteractor: onStop");
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
}
