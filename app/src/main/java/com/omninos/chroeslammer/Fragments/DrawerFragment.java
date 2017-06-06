package com.omninos.chroeslammer.Fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.omninos.chroeslammer.Activities.Favourite_Chore;
import com.omninos.chroeslammer.Activities.LoginActivity;
import com.omninos.chroeslammer.Activities.NotificationLIst;
import com.omninos.chroeslammer.Activities.Profile;
import com.omninos.chroeslammer.Activities.MainActivity;
import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.Helper.Retrofit_Instance;
import com.omninos.chroeslammer.Helper.SharedPrefManager;
import com.omninos.chroeslammer.Interface.Get_Profile_Api;
import com.omninos.chroeslammer.Models.GetProfile_Response_Model;
import com.omninos.chroeslammer.Models.Get_Profile_Result;
import com.omninos.chroeslammer.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment implements View.OnClickListener {

    private LinearLayout closeBtn, profile, favourite_chore, logout, home;
    Activity activity;
    private RelativeLayout notification_rl;
    private ProgressDialog progressDialog;


    public DrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);


        closeBtn = (LinearLayout) view.findViewById(R.id.closeBtn);
        home = (LinearLayout) view.findViewById(R.id.home);
        profile = (LinearLayout) view.findViewById(R.id.profile);
        // notification_rl = (RelativeLayout) view.findViewById(R.id.nofification_ll);
        logout = (LinearLayout) view.findViewById(R.id.goto_login);
        //  favourite_chore = (LinearLayout) view.findViewById(R.id.favourite_chore_slammer_ll);

        closeBtn.setOnClickListener(this);
        profile.setOnClickListener(this);
        //  notification_rl.setOnClickListener(this);
        //  favourite_chore.setOnClickListener(this);
        logout.setOnClickListener(this);
        home.setOnClickListener(this);

        activity = getActivity();

        progressDialog = new ProgressDialog(activity);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.closeBtn:
                MainActivity.drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.profile:
                progressDialog.setMessage("Getting Profile Details..");
                progressDialog.show();
                String user_id = getUserId();
                getprofileDetails(user_id);
                // startActivity(new Intent(activity, Profile.class));
                break;

           /* case R.id.nofification_ll:
                startActivity(new Intent(activity, NotificationLIst.class));
                MainActivity.drawerLayout.closeDrawer(GravityCompat.START);
                break;*/

            /*case R.id.favourite_chore_slammer_ll:
                startActivity(new Intent(activity, Favourite_Chore.class));
                MainActivity.drawerLayout.closeDrawer(GravityCompat.START);
                break;*/

            case R.id.goto_login:
                SharedPreferences sharedPreferences = activity.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(activity, LoginActivity.class));
                break;
            case R.id.home:
                startActivity(new Intent(activity, MainActivity.class));
                break;

        }
    }

    private void getprofileDetails(String userid) {
        final Get_Profile_Api get_profile_api = Retrofit_Instance.getRetrofit().create(Get_Profile_Api.class);
        get_profile_api.getProfileDetails(userid, new Callback<GetProfile_Response_Model>() {
            @Override
            public void success(GetProfile_Response_Model getProfile_response_model, Response response) {
                String message = getProfile_response_model.getMessage();
                String success = getProfile_response_model.getSuccess();
                if (success.matches("1")) {
                    Get_Profile_Result get_profile_result = getProfile_response_model.get_profile_result();
                    //Get_Profile_Result list = getProfile_response_model.get_profile_result();
                    //  String n = get_profile_result.getEmail();
                    String username = get_profile_result.getUsername();
                    String image = get_profile_result.getImage();
                    Log.d("myyyimaggeeeee", image);
                    String category = get_profile_result.getCategory();
                    String second_category = get_profile_result.getSecondCategory();
                    String address = get_profile_result.getAddress();
                    String id = get_profile_result.getId();
                    Intent intent = new Intent(activity, Profile.class);
                    intent.putExtra(Constants.USERNAME, username);
                    intent.putExtra(Constants.IMAGE, image);
                    intent.putExtra(Constants.CATEGORY, category);
                    intent.putExtra(Constants.SECOND_CATEGORY, second_category);
                    intent.putExtra(Constants.ADDRESS, address);
                    startActivity(intent);
                    progressDialog.dismiss();
                    MainActivity.drawerLayout.closeDrawer(GravityCompat.START);
                }


            }

            @Override
            public void failure(RetrofitError error) {

                progressDialog.dismiss();
                Toast.makeText(activity, "Failed to get Details", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String getUserId() {
        String user_id = SharedPrefManager.getInstance(activity).getUserId();
        return user_id;
    }
}
