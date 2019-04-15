package com.github.bitlinker.radioman.ui;

import android.os.Bundle;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.navigation.MainNavigator;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatActivity;
import com.github.bitlinker.radioman.ui.moxyx.SupportXAppNavigator;

import javax.annotation.Nullable;

import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.Navigator;

public class MainActivity extends MvpXAppCompatActivity {
    // TODO: inject
    private MainNavigator mainNavigator = new MainNavigator();

    private final Navigator navigator = new SupportXAppNavigator(this, getSupportFragmentManager(), R.id.content);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: inject
        Injector.getInstance().createUIComponent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Intent intent = new Intent(this, PlayerFragment.class);
//        startActivity(intent);

        // TODO: from business/presenter
        //mainNavigator.toMainScreen();
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
        super.onDestroy();
    }
}
