package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.omninos.chroeslammer.Adapters.NotificationAdapter;
import com.omninos.chroeslammer.Adapters.PostList_Adapter;
import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.Helper.Retrofit_Instance;
import com.omninos.chroeslammer.Interface.Get_JobList_Api;
import com.omninos.chroeslammer.Models.JobList;
import com.omninos.chroeslammer.Models.Job_List_Response_Model;
import com.omninos.chroeslammer.Models.Notification_Model;
import com.omninos.chroeslammer.R;


import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PostList extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    List<JobList> list;
    Intent intent;
   // List<String> stringList;
    PostList_Adapter postList_adapter;
    Job_List_Response_Model job_list_response_model;
    private LinearLayout menuLT;
    private ImageView menu;
    public static DrawerLayout drawerLayout;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Getting Chores To Pick");
        progressDialog.show();


        showList();

      /*  intent = getIntent();
        String lists = intent.getStringExtra(Constants.JOBLIST);
        list = (ArrayList<JobList>)lists;
        list = (List<JobList>) intent.getExtras().getSerializable(Constants.JOBLIST);
        Log.d("mylisttttttttttt", String.valueOf(list));*/

        menu = (ImageView)findViewById(R.id.menu);

        menu.setVisibility(View.GONE);

        menuLT = (LinearLayout) findViewById(R.id.menuLT);

        drawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);

        menuLT.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




    }

    private void showList()
    {
        Get_JobList_Api get_jobList_api = Retrofit_Instance.getRetrofit().create(Get_JobList_Api.class);

        get_jobList_api.getList(new Callback<Job_List_Response_Model>() {
            @Override
            public void success(Job_List_Response_Model job_list_response_model, Response response) {



                String success = job_list_response_model.getSuccess();
                String message = job_list_response_model.getMessage();
                list = job_list_response_model.getJobList();
                Log.d("myylisssttt", String.valueOf(list));
                if (success.matches("1"))
                {
                    Intent startintent = new Intent(PostList.this,PostList.class);
                    postList_adapter = new PostList_Adapter(PostList.this,list);
                    postList_adapter.addList(list);
                    recyclerView.setAdapter(postList_adapter);
                    progressDialog.dismiss();
                }
                else
                {
                    Toast.makeText(PostList.this, "Failed to get chores", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                progressDialog.dismiss();
                Toast.makeText(PostList.this, "failde to get list", Toast.LENGTH_SHORT).show();
                Log.e("retrofit error", String.valueOf(error));
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.menuLT:
                drawerLayout.openDrawer(GravityCompat.START);
                break;


        }

    }
}
