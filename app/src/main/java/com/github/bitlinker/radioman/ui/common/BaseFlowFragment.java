package com.github.bitlinker.radioman.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.ui.moxyx.SupportXAppNavigator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.terrakok.cicerone.Navigator;

public abstract class BaseFlowFragment extends BaseFragment {
    private Navigator navigator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigator = new SupportXAppNavigator(getActivity(), getChildFragmentManager(), R.id.content); // TODO: get content resource
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO: handle navigator back?
    }

    @Override
    public void onResume() {
        super.onResume();
        getMainNavigator().attach(navigator);
    }

    @Override
    public void onPause() {
        getMainNavigator().detach();
        super.onPause();
    }
}
