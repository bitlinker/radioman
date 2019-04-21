package com.github.bitlinker.radioman.di.ui;

import com.github.bitlinker.radioman.di.app.AppComponent;
import com.github.bitlinker.radioman.ui.MainActivity;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.github.bitlinker.radioman.ui.history.HistoryFragment;
import com.github.bitlinker.radioman.ui.main.bottomplayer.BottomPlayerFragment;
import com.github.bitlinker.radioman.ui.main.radiolist.RadioListFragment;
import com.github.bitlinker.radioman.ui.player.PlayerFragment;

import dagger.Component;

@UiScope
@Component(modules = {MainActivityModule.class, NavigationModule.class, FragmentPresentersModule.class}, dependencies = {AppComponent.class})
public interface UiComponent {
    void inject(MainActivity target);

    void inject(BaseFragment target);

    void inject(HistoryFragment target);

    void inject(RadioListFragment target);

    void inject(BottomPlayerFragment target);

    void inject(PlayerFragment target);
}
