package com.github.bitlinker.radioman.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.ui.common.BaseFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public class MainFragment extends BaseFragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_settings);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_settings_settings) {
                // TODO: navigate
                return true;
            }
            return false;
        });
    }
}
