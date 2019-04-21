package com.github.bitlinker.radioman.ui.common;

import android.os.Bundle;

import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.navigation.MainNavigator;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatFragment;

import javax.inject.Inject;

public abstract class BaseFragment extends MvpXAppCompatFragment {
    @Inject
    MainNavigator mainNavigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: inject per-instance?
        Injector.getInstance().getUIComponent(getActivity()).inject(this);
        super.onCreate(savedInstanceState);
    }

    public MainNavigator getMainNavigator() {
        return mainNavigator;
    }

    public void onBackPressed() {
        mainNavigator.back();
    }
}
