package com.example.android.quakereport;

import android.preference.Preference;
import android.preference.PreferenceFragment;

abstract class EarthquakePreferenceFragment extends PreferenceFragment {
    public abstract boolean onPreferenceChange(Preference preference, Object value);
}
