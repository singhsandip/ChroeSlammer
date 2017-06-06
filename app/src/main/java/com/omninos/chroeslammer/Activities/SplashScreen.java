package com.omninos.chroeslammer.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.omninos.chroeslammer.Helper.SharedPrefManager;
import com.omninos.chroeslammer.R;

import static android.os.Build.VERSION_CODES.M;


public class SplashScreen extends AppCompatActivity {


    private static final int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        if (hasPermissions()) {
            initialization();

        } else {
            requestNecessaryPermissions();
        }


    }






        //.............go to next activity................//


    private boolean hasPermissions() {
        int res = 0;
        // list all permissions which you want to check are granted or not.
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA,
        };
        for (String perms : permissions) {
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                // it return false because your app dosen't have permissions.
                return false;
            }

        }
        // it return true, your app has permissions.
        return true;
    }

    private void requestNecessaryPermissions() {
        // make array of permissions which you want to ask from user.
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};


        if (Build.VERSION.SDK_INT >= M) {
            // have arry for permissions to requestPermissions method.
            // and also send unique Request code.
            requestPermissions(permissions, REQUEST_PERMISSION_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grandResults) {
        // this boolean will tell us that user granted permission or not.
        boolean allowed = true;
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                for (int res : grandResults) {
                    // if user granted all required permissions then 'allowed' will return true.
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                }
                break;
            default:
                // if user denied then 'allowed' return false.
                allowed = false;
                break;
        }
        if (allowed) {

            initialization();
        } else {
            // else give any custom waring message.
            if (Build.VERSION.SDK_INT >= M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(SplashScreen.this, "Location Permissions denied", Toast.LENGTH_SHORT).show();
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(SplashScreen.this, "Read Permissions denied", Toast.LENGTH_SHORT).show();
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    Toast.makeText(SplashScreen.this, "Camera Permissions denied", Toast.LENGTH_SHORT).show();
                }


            }

        }
    }

    private void initialization() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                String userid = SharedPrefManager.getInstance(SplashScreen.this).getUserId();


                if (userid.equalsIgnoreCase(""))
                {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();

                }
                else
                {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }

                // Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                // startActivity(i);

            }
        }, 3000);
    }
    }


