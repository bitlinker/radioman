package com.github.bitlinker.radioman.ui;

import android.os.Bundle;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.navigation.MainNavigator;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatActivity;
import com.github.bitlinker.radioman.ui.moxyx.SupportXAppNavigator;

import javax.annotation.Nullable;
import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;

public class MainActivity extends MvpXAppCompatActivity {
    @Inject
    MainNavigator mainNavigator;

    private final Navigator navigator = new SupportXAppNavigator(this, getSupportFragmentManager(), R.id.content) {
        @Override
        protected void setupFragmentTransaction(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
            super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction);
            // TODO: setup animations
            fragmentTransaction.setCustomAnimations(R.anim.transition_enter_right, R.anim.transition_exit_left); // TODO: config; common
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Injector.getInstance().getUIComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Intent intent = new Intent(this, PlayerFragment.class);
//        startActivity(intent);

        // TODO: call from business/presenter
        navigateToStartScreen();
    }

    // TODO: view method
    public void navigateToStartScreen() {
        mainNavigator.toPlayerScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainNavigator.attach(navigator);
    }

    @Override
    protected void onPause() {
        mainNavigator.detach();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        BaseFragment curFragment = getCurFragment();
        if (curFragment != null) {
            curFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private @Nullable
    BaseFragment getCurFragment() {
        Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.content);
        if (fragmentById instanceof BaseFragment) {
            return (BaseFragment) fragmentById;
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        Injector.getInstance().destroyUIComponent(this);
        super.onDestroy();
    }
}
