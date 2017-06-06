package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.provider.MediaStore;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.R;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout menuLT;
    private ImageView profile_pic,menu;
    public static DrawerLayout drawerLayout;
    private String user_id,username,image,category,second_category,address;
    private TextView user_name_tv,cateegory_tv,second_category_tv,address_tv;
    private ProgressDialog progressDialog;
    private Intent start_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        idsIntialization();

        setProfileDetails();


        menuLT.setOnClickListener(this);
        menu.setOnClickListener(this);



    }



    private void idsIntialization()
    {
        user_name_tv = (TextView)findViewById(R.id.user_name_tv);
        cateegory_tv = (TextView) findViewById(R.id.category_text_view);
        second_category_tv = (TextView) findViewById(R.id.second_cat_tv);
        address_tv = (TextView) findViewById(R.id.address_tv);
        profile_pic = (ImageView)findViewById(R.id.profile_pic);

        //image = "sandeep";

        menu = (ImageView)findViewById(R.id.menu);

        menu.setVisibility(View.VISIBLE);

        progressDialog = new ProgressDialog(this);

        menuLT = (LinearLayout) findViewById(R.id.menuLT);

        drawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);
    }

    private void setProfileDetails()
    {

        Intent intent = getIntent();

        username = intent.getStringExtra(Constants.USERNAME);

      //  Log.e("profile user name",username);

        image = intent.getStringExtra(Constants.IMAGE);

        if (image.matches(""))
        {

        }
       else
        {
            Picasso.with(this).load(image).into(profile_pic);
          //  Log.e("profile iumage",image );



            category = intent.getStringExtra(Constants.CATEGORY);

           // Log.e("profile categoryt",category );

            second_category = intent.getStringExtra(Constants.SECOND_CATEGORY);

           // Log.e("profile second_cat",second_category );

            StringBuilder string_builder = new StringBuilder(second_category);
            string_builder.insert(0,"(").append(")");


            category = intent.getStringExtra(Constants.CATEGORY);
            address = intent.getStringExtra(Constants.ADDRESS);

            //Log.e("addreess iumage",address );

            user_name_tv.setText(username);

            cateegory_tv.setText(category);
            second_category_tv.setText(string_builder);
            address_tv.setText(address);
        }

        /*Log.e("profile iumage",image );



        category = intent.getStringExtra(Constants.CATEGORY);

        Log.e("profile categoryt",category );

        second_category = intent.getStringExtra(Constants.SECOND_CATEGORY);

        Log.e("profile second_cat",second_category );

        StringBuilder string_builder = new StringBuilder(second_category);
        string_builder.insert(0,"(").append(")");


        category = intent.getStringExtra(Constants.CATEGORY);
        address = intent.getStringExtra(Constants.ADDRESS);

        Log.e("addreess iumage",address );

        user_name_tv.setText(username);

        cateegory_tv.setText(category);
        second_category_tv.setText(string_builder);
        address_tv.setText(address);*/
    }


    @Override
    public void onClick(View v) {

       // Intent start_intent;
        switch (v.getId()) {
            case R.id.menuLT:
                drawerLayout.openDrawer(GravityCompat.START);
                break;


            case R.id.menu:
                showDialog();
                break;


        }

    }

    private void showDialog()
    {
        AlertDialog.Builder myAlertDilog = new AlertDialog.Builder(Profile.this);

        myAlertDilog.setNegativeButton("Edit Profile", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // start_intent = new Intent(Profile.this,Update_Profile_Activity.class);
                start_intent = new Intent(Profile.this,Update_Profile_Activity.class);
                start_intent.putExtra(Constants.USERNAME,username);
                start_intent.putExtra(Constants.IMAGE,image);
                start_intent.putExtra(Constants.CATEGORY,category);
                start_intent.putExtra(Constants.ADDRESS,address);
                start_intent.putExtra(Constants.SECOND_CATEGORY,second_category);

                startActivity(start_intent);
            }
        });
           AlertDialog dialog = myAlertDilog.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wmlp.x = 0;   //x position
        wmlp.y = 10;   //y position

        dialog.show();
        dialog.getWindow().setLayout(350, 180);
    }


}
