package com.omninos.chroeslammer.Activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.omninos.chroeslammer.Adapters.NotificationAdapter;
import com.omninos.chroeslammer.Models.Notification_Model;
import com.omninos.chroeslammer.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationLIst extends AppCompatActivity  implements View.OnClickListener{

    RecyclerView recyclerView;
    List<Notification_Model> list;
    List<String> stringList;
    NotificationAdapter notificationAdapter;
    Notification_Model notification_model;
    private LinearLayout menuLT;
    private ImageView menu;
    public static DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        menu = (ImageView)findViewById(R.id.menu);

        menu.setVisibility(View.GONE);

        menuLT = (LinearLayout) findViewById(R.id.menuLT);

        drawerLayout = (DrawerLayout) findViewById(R.id.drwaer_layout);

        menuLT.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        stringList = new ArrayList<>();

        stringList.add("sandeepp");
        stringList.add("sandeepp");
        stringList.add("sandeepp");
        stringList.add("sandeepp");
        stringList.add("sandeepp");
        stringList.add("sandeepp");

        for (int i=0; i<stringList.size(); i++)
        {
            notification_model = new Notification_Model(stringList.get(i),stringList.get(i));
            list.add(notification_model);
        }

        notificationAdapter = new NotificationAdapter(this,list);
        recyclerView.setAdapter(notificationAdapter);
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
