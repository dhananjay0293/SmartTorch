package com.androidtutorialpoint.ledflashlight;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WifiTransparentActivity extends AppCompatActivity {

    WifiReciever broadcastReceiver;

    public static WifiTransparentActivity ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_trasperant);
        //broadCastIntent();
        //register();
    }

    public void broadCastIntent(){
        Intent intent=new Intent();
        intent.setAction("com.androidtutorialpoint.ledflashlight.WifitransparentActivity");
        sendBroadcast(intent);
    }

    public void register(){
        broadcastReceiver=new WifiReciever();
        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        super.registerReceiver(broadcastReceiver, filters);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //unregisterReceiver(broadcastReceiver);
    }
}
