package com.example.jengtallis.simpleui;

import android.os.AsyncTask;

/**
 * Created by alog1024 on 8/18/16.
 */
public class GeoCodingTask extends AsyncTask <String, Void, double[]>{

    @Override
    protected double[] doInBackground(String... params) {
        return new double[0];
    }

    @Override
    protected void onPostExecute(double[] doubles) {
        super.onPostExecute(doubles);
    }
}
