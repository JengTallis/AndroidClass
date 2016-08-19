package com.example.jengtallis.simpleui;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by alog1024 on 8/18/16.
 */
public class GeoCodingTask extends AsyncTask <String, Void, double[]>{

    WeakReference<GeoCodingCallback> geoCodingCallbackWeakReference;

    //    background thread's job
    @Override
    protected double[] doInBackground(String... params) {
        return Utils.getLatLngFromAddress(params[0]);
    }


    @Override
    protected void onPostExecute(double[] doubles) {
        super.onPostExecute(doubles);
        if(geoCodingCallbackWeakReference.get() != null){
            GeoCodingCallback geoCodingCallback = geoCodingCallbackWeakReference.get();
            geoCodingCallback.done(doubles);
        }
    }

    public GeoCodingTask(GeoCodingCallback geoCodingCallback){
        geoCodingCallbackWeakReference = new WeakReference<GeoCodingCallback>(geoCodingCallback);
    }

    interface GeoCodingCallback{
        void done(double[] latlng);
    }

}
