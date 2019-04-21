package com.github.bitlinker.radioman.ui.main.radiolist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.github.bitlinker.radioman.domain.Radio;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface RadioListView extends MvpView {
    void setList(List<Radio> list);
}
