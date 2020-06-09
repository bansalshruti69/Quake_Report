package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String murl;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        murl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<Earthquake> loadInBackground() {
        if (murl == null) {
            return null;
        }

        List<Earthquake> result = QueryUtils.fetchEarthquakeData(murl);
        return result;
    }
}
