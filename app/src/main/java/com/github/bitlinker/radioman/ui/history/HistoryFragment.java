package com.github.bitlinker.radioman.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.domain.HistoryItem;
import com.github.bitlinker.radioman.ui.common.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.Lazy;

public class HistoryFragment extends BaseFragment implements HistoryView {
    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    private RecyclerView rvList;

    // TODO: think of some cool dagger hack?
    @InjectPresenter
    HistoryPresenter presenter;

    @Inject
    Lazy<HistoryPresenter> presenterProvider;

    @ProvidePresenter
    HistoryPresenter providePresenter() {
        return presenterProvider.get();
    }

    private HistoryItemAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: base injection override?
        Injector.getInstance().getUIComponent(getActivity()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public @Nullable
    View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
        initList(view);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.history_fragment_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (R.id.menu_history_clearall == item.getItemId()) {
                presenter.onToolbarClearAllClicked();
                return true;
            }
            return false;
        });
        toolbar.setNavigationOnClickListener(v -> presenter.onToolbarBackClicked());
    }

    private void initList(View view) {
        rvList = view.findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new HistoryItemAdapter(getActivity().getLayoutInflater());
        rvList.setAdapter(adapter);
    }

    @Override
    public void navigateBack() {
        onBackPressed();
    }

    @Override
    public void setList(List<HistoryItem> list) {
        adapter.submitList(list);
    }

    @Override
    public void showError() {
        // TODO: impl
        // TODO: common way to show errors?
    }
}
