package com.omninos.chroeslammer.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.omninos.chroeslammer.Helper.SharedPrefManager;

/**
 * Created by sandeep on 17-05-2017.
 */

public class FireBaseInstanceService extends FirebaseInstanceIdService
{
    private static final String TAG = "MyFirebaseIIDService";


    @Override
    public void onTokenRefresh() {


        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed token: " + token);

        storeToken(token);

    }

    private void storeToken(String token)
    {

        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
