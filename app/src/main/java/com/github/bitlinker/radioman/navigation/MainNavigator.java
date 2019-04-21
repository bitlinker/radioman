package com.github.bitlinker.radioman.navigation;

import com.github.bitlinker.radioman.ui.history.HistoryFragment;
import com.github.bitlinker.radioman.ui.main.MainFragment;
import com.github.bitlinker.radioman.ui.moxyx.SupportXAppScreen;
import com.github.bitlinker.radioman.ui.player.PlayerFragment;
import com.github.bitlinker.radioman.ui.settings.SettingsFragment;

import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;

public class MainNavigator {
    private static SupportXAppScreen MAIN_SCREEN = new SupportXAppScreen() {
        @Override
        public Fragment getFragment() {
            return MainFragment.newInstance();
        }
    };

    private static SupportXAppScreen PLAYER_SCREEN = new SupportXAppScreen() {
        @Override
        public Fragment getFragment() {
            return PlayerFragment.newInstance();
        }
    };

    private static SupportXAppScreen SETTINGS_SCREEN = new SupportXAppScreen() {
        @Override
        public Fragment getFragment() {
            return SettingsFragment.newInstance();
        }
    };

    private static SupportXAppScreen HISTORY_SCREEN = new SupportXAppScreen() {
        @Override
        public Fragment getFragment() {
            return HistoryFragment.newInstance();
        }
    };

    private final Cicerone<Router> cicerone = Cicerone.create();

    public void attach(Navigator navigator) {
        cicerone.getNavigatorHolder().setNavigator(navigator);
    }

    public void detach() {
        cicerone.getNavigatorHolder().removeNavigator();
    }

    public void back() {
        cicerone.getRouter().exit();
    }

    public void toMainScreen() {
        cicerone.getRouter().newRootScreen(MAIN_SCREEN);
    }

    public void toPlayerScreen() {
        cicerone.getRouter().navigateTo(PLAYER_SCREEN);
    }

    public void toSettingsScreen() {
        cicerone.getRouter().navigateTo(SETTINGS_SCREEN);
    }

    public void toHistoryScreen() {
        cicerone.getRouter().navigateTo(HISTORY_SCREEN);
    }
}
