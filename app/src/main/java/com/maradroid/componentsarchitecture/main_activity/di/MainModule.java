package com.maradroid.componentsarchitecture.main_activity.di;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;

import com.maradroid.componentsarchitecture.base.PerActivity;
import com.maradroid.componentsarchitecture.main_activity.interactor.MainInteractor;
import com.maradroid.componentsarchitecture.main_activity.presenter.MainViewModel;
import com.maradroid.componentsarchitecture.main_activity.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mara on 6/4/17.
 */
@Module
public class MainModule {

    @Provides
    @PerActivity
    MainInteractor provideMainInteractor(MainActivity mainActivity) {
        return new MainInteractor(mainActivity.getLifecycle());
    }

    @PerActivity
    @Provides
    MainViewModel provideMainViewModel(MainInteractor interactor) {
        //return ViewModelProviders.of(mainActivity).get(MainViewModel.class);
        return new MainViewModel(interactor);
    }
}
