package com.github.bitlinker.radioman.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bitlinker.radioman.ui.common.BaseFragment;

import androidx.annotation.Nullable;

public class HistoryFragment extends BaseFragment implements HistoryView {
    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public @Nullable
    View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
