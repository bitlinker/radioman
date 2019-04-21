package com.github.bitlinker.radioman.ui.main.radiolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.domain.Radio;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.Lazy;

public class RadioListFragment extends BaseFragment implements RadioListView {
    private static final int ROW_COUNT = 2; // TODO: switch based on screen size???

    private RecyclerView rvList;
    private FloatingActionButton fabAdd;
    private RadioListAdapter adapter;

    @InjectPresenter
    RadioListPresenter presenter;

    @Inject
    Lazy<RadioListPresenter> presenterProvider;

    @ProvidePresenter
    public RadioListPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Injector.getInstance().getUIComponent(getActivity()).inject(this);
        super.onCreate(savedInstanceState);
    }

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

        fabAdd.setOnClickListener(v -> presenter.onAddClicked());

        rvList.setLayoutManager(new GridLayoutManager(getActivity(), ROW_COUNT));
        adapter = new RadioListAdapter(getActivity().getLayoutInflater());
        rvList.setAdapter(adapter);
    }

    @Override
    public void setList(List<Radio> list) {
        adapter.submitList(list);
    }
}
