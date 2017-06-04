package com.maradroid.componentsarchitecture.base;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by mara on 6/4/17.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityModule.class})
public interface AppComponent {
    void inject(MyApp app);

    // svi ovo imaju ali ocegledno radi i bez toga(barem u ovom slucaju)
    /*@Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApp application);

        AppComponent build();
    }*/
}
