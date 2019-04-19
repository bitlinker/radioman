package com.github.bitlinker.radioman.di.ui;

import com.github.bitlinker.radioman.di.app.AppComponent;
import com.github.bitlinker.radioman.ui.MainActivity;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.github.bitlinker.radioman.ui.player.PlayerFragment;

import dagger.Component;

@UiScope
@Component(modules = {MainActivityModule.class, NavigationModule.class}, dependencies = {AppComponent.class})
public interface UiComponent {
    void inject(MainActivity target);

    //void inject(PlayerFragment target);
    void inject(BaseFragment target);
}
