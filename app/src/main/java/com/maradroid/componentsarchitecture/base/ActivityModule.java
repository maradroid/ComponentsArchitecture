package com.maradroid.componentsarchitecture.base;

import com.maradroid.componentsarchitecture.main_activity.di.MainModule;
import com.maradroid.componentsarchitecture.main_activity.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mara on 6/4/17.
 */
@Module
abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivityInjector();
}
