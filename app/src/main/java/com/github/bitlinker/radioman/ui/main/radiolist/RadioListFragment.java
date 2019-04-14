package com.github.bitlinker.radioman.ui.main.radiolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RadioListFragment extends BaseFragment {
    private RecyclerView rvList;

    private FloatingActionButton fabAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.radiolist_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvList = view.findViewById(R.id.rvList);
        fabAdd = view.findViewById(R.id.fabAdd);

        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // TODO: const
        //rvList.setAdapter();
        // TODO: list adapter
    }
}
