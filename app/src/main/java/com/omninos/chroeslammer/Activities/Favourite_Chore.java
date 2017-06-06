package com.omninos.chroeslammer.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.omninos.chroeslammer.Adapters.Favourite_Chore_Adapter;
import com.omninos.chroeslammer.Adapters.NotificationAdapter;
import com.omninos.chroeslammer.Adapters.SwipeRecylerViewAdapter;
import com.omninos.chroeslammer.Models.Favourite_Chore_Model;
import com.omninos.chroeslammer.Models.Notification_Model;
import com.omninos.chroeslammer.R;

import java.util.ArrayList;
import java.util.List;

public class Favourite_Chore extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    List<Favourite_Chore_Model> list;
    List<String> stringList;
    SwipeRecylerViewAdapter swipeRecylerViewAdapter;
    Favourite_Chore_Model favourite_chore_model;
    private LinearLayout menuLT;
    private ImageView menu;
    public static DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        menuLT = (LinearLayout) findViewById(R.id.menuLT);

        menu = (ImageView)findViewById(R.id.menu);

        menu.setVisibility(View.GONE);

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
            favourite_chore_model = new Favourite_Chore_Model(stringList.get(i),stringList.get(i),stringList.get(i));
            list.add(favourite_chore_model);
        }

        swipeRecylerViewAdapter = new SwipeRecylerViewAdapter(this,list);
        recyclerView.setAdapter(swipeRecylerViewAdapter);

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



