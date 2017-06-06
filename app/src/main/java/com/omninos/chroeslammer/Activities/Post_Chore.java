package com.omninos.chroeslammer.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.omninos.chroeslammer.R;

import java.io.IOException;
import java.util.List;

public class Post_Chore extends AppCompatActivity implements View.OnClickListener {



    private LinearLayout menuLT,contractor,electrical,plumbing,moving_service,landspacing,painting,home_cleaning;
    public static DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);



            drawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);

            menuLT = (LinearLayout) findViewById(R.id.menuLT);
            contractor = (LinearLayout) findViewById(R.id.contractor);
            electrical = (LinearLayout) findViewById(R.id.electrical);
            painting = (LinearLayout) findViewById(R.id.painting);
            plumbing = (LinearLayout) findViewById(R.id.plumbing);
            moving_service = (LinearLayout) findViewById(R.id.moving_service);
            landspacing = (LinearLayout) findViewById(R.id.landscaping);
            home_cleaning = (LinearLayout) findViewById(R.id.home_cleaning);

            menuLT.setOnClickListener(this);
            contractor.setOnClickListener(this);
            electrical.setOnClickListener(this);
            plumbing.setOnClickListener(this);
            painting.setOnClickListener(this);
            moving_service.setOnClickListener(this);
            landspacing.setOnClickListener(this);
            home_cleaning.setOnClickListener(this);
        }


    @Override
    public void onClick(View v) {

        String type = "type";
        Intent intent;

        switch (v.getId()) {
            case R.id.menuLT:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.contractor:
                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Contractor");
                startActivity(intent);
                break;

            case R.id.electrical:

                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Electrical");
                startActivity(intent);
                break;

            case R.id.painting:
                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Painting");
                startActivity(intent);
            break;

            case R.id.plumbing:
                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Plumbing");
                startActivity(intent);
                break;

            case R.id.landscaping:
                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Landscaping");
                startActivity(intent);
                break;

            case R.id.home_cleaning:
                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Home Cleaning");
                startActivity(intent);
                break;

            case R.id.moving_service:
                intent = new Intent(Post_Chore.this,PostChoreActivity.class);
                intent.putExtra(type,"Moving Service");
                startActivity(intent);
                break;

        }
    }
}
