package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.omninos.chroeslammer.Helper.Check_Internet_Connection;
import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.Helper.Retrofit_Instance;
import com.omninos.chroeslammer.Interface.Get_JobList_Api;
import com.omninos.chroeslammer.Models.JobList;
import com.omninos.chroeslammer.Models.Job_List_Response_Model;
import com.omninos.chroeslammer.R;

import java.io.Serializable;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static DrawerLayout drawerLayout;
    private LinearLayout menuLT;
    private ProgressDialog progressDialog;
    private ImageView post_chore_imageview,pick_a_chore_imageview,menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        progressDialog = new ProgressDialog(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);
        menuLT = (LinearLayout) findViewById(R.id.menuLT);
        post_chore_imageview = (ImageView)findViewById(R.id.post_chore_img_view);
        pick_a_chore_imageview = (ImageView)findViewById(R.id.pick_a_chore_img_view);
        menu = (ImageView)findViewById(R.id.menu);

        menu.setVisibility(View.GONE);

        menuLT.setOnClickListener(this);
        post_chore_imageview.setOnClickListener(this);
        pick_a_chore_imageview.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.menuLT:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.post_chore_img_view:

                startActivity(new Intent(MainActivity.this, Post_Chore.class));

                /*if (gpsTracker.canGetLocation())
                {
                    startActivity(new Intent(MainActivity.this, Post_Chore.class));
                }
                else
                {
                    gpsTracker.showSettingsAlert();
                }*/

                break;

            case R.id.pick_a_chore_img_view:

                startActivity(new Intent(MainActivity.this, PostList.class));

                break;


        }
    }
}
