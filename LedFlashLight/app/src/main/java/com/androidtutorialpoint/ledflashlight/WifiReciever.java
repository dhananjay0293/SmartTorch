package com.androidtutorialpoint.ledflashlight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;


public class WifiReciever extends BroadcastReceiver {
    WifiTransparentActivity main;
    CameraManager mCameraManager;
    String mCameraId;

    public WifiReciever(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            ConnectivityManager connManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiCheck = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            WifiManager mWifi=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info=(WifiInfo)mWifi.getConnectionInfo();
            if(wifiCheck.getState().equals(NetworkInfo.State.DISCONNECTED) && !((info.getSupplicantState().equals(SupplicantState.SCANNING)))){
                Boolean isFlashAvailable = context.getApplicationContext().getPackageManager()
                        .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

                /*if (!isFlashAvailable) {
                    AlertDialog alert = new AlertDialog.Builder(context)
                            .create();
                    alert.setTitle("Error !!");
                    alert.setMessage("Your device doesn't support flash light!");
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // closing the application
                            finish();
                            System.exit(0);
                        }
                    });
                    alert.show();
                    return;
                }*/

                mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                try {
                    mCameraId = mCameraManager.getCameraIdList()[0];
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                turnOnFlashLight();
            }
        }
    }

    public void turnOnFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
                //mTorchOnOffButton.setImageResource(R.drawable.on);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


