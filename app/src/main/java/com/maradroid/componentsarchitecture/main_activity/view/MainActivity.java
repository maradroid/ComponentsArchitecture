package com.maradroid.componentsarchitecture.main_activity.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.maradroid.componentsarchitecture.R;
import com.maradroid.componentsarchitecture.base.MyApp;
import com.maradroid.componentsarchitecture.main_activity.di.MainModule;
import com.maradroid.componentsarchitecture.main_activity.presenter.MainViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends LifecycleActivity {

    @BindView(R.id.tv)
    TextView textView;

    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("maradroid", "MainActivity: onCreate");
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ButterKnife.bind(this);

        viewModel.getTimeData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        viewModel.getErrorData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("maradroid", "MainActivity: onStart");
        viewModel.startCounting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("maradroid", "MainActivity: onStop");
    }
}
