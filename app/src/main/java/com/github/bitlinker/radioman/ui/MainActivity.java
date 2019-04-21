package com.github.bitlinker.radioman.ui;

import android.os.Bundle;
import android.view.View;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.navigation.MainNavigator;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.github.bitlinker.radioman.ui.error.UIErrorBus;
import com.github.bitlinker.radioman.ui.error.UiError;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatActivity;
import com.github.bitlinker.radioman.ui.moxyx.SupportXAppNavigator;
import com.google.android.material.snackbar.Snackbar;

import javax.annotation.Nullable;
import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.disposables.CompositeDisposable;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;

public class MainActivity extends MvpXAppCompatActivity {
    @Inject
    MainNavigator mainNavigator;

    @Inject
    UIErrorBus UIErrorBus;

    private View rootView;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final Navigator navigator = new SupportXAppNavigator(this, getSupportFragmentManager(), R.id.content) {
        @Override
        protected void setupFragmentTransaction(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
            super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction);
            // TODO: setup animations
            //fragmentTransaction.setCustomAnimations(R.anim.transition_enter_right, R.anim.transition_exit_left); // TODO: config; common
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Injector.getInstance().getUIComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        rootView = findViewById(R.id.content);
//        Intent intent = new Intent(this, PlayerFragment.class);
//        startActivity(intent);

        // TODO: call from business/presenter
        navigateToStartScreen();
        //mainNavigator.toHistoryScreen();
    }

    // TODO: view method
    public void navigateToStartScreen() {
        mainNavigator.toMainScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainNavigator.attach(navigator);
        disposable.add(
                UIErrorBus.observable()
                        .subscribe(this::showError)
        );
    }

    private void showError(UiError error) {
        int strId;
        switch (error.getType()) {
            case IO:
                strId = R.string.error_io;
                break;
            case NETWORK:
                strId = R.string.error_network;
                break;
            default:
                strId = R.string.error_unknown;
        }
        Snackbar.make(rootView, strId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        disposable.dispose();
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
