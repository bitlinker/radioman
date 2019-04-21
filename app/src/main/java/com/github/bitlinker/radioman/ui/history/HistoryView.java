package com.github.bitlinker.radioman.ui.history;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.github.bitlinker.radioman.domain.HistoryItem;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HistoryView extends MvpView {
    void setList(List<HistoryItem> list);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void navigateBack();
}
