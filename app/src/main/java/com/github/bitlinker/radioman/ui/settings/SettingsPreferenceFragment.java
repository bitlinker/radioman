package com.github.bitlinker.radioman.ui.settings;

import android.os.Bundle;

import com.github.bitlinker.radioman.R;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsPreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, null);
    }
}
