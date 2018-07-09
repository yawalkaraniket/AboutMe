package com.aboutme.avenjr.aboutme.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class NetworkService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if(!isConnectedToInternet()){
            Log.d("Network Service","please connect to internet");
        }
        Log.d("Service","onbind started");
        return null;
    }

    @Override
    public void onCreate() {
        if(!isConnectedToInternet()){
            Log.d("Network Service","please connect to internet");
        }
        Log.d("Service","onCreate started");
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        Log.d("Service","onLowMenory started");
        super.onLowMemory();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service","onStartCommend started");
        if(!isConnectedToInternet()){
            Log.d("Network Service","please connect to internet");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public ComponentName startService(Intent service) {
        if(!isConnectedToInternet()){
            Log.d("Network Service","please connect to internet");
        }
        return super.startService(service);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if(!isConnectedToInternet()){
            Log.d("Network Service","please connect to internet");
        }
        Log.d("Service","onUnbind started");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("Service","onDestroy started");
        super.onDestroy();
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
