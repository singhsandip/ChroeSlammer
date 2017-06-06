package com.omninos.chroeslammer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.omninos.chroeslammer.Adapters.Favourite_Chore_Adapter;
import com.omninos.chroeslammer.Adapters.SwipeRecylerViewAdapter;
import com.omninos.chroeslammer.Models.Favourite_Chore_Model;
import com.omninos.chroeslammer.R;

import java.util.ArrayList;
import java.util.List;

public class Extra extends AppCompatActivity {


    RecyclerView recyclerView;
    List<Favourite_Chore_Model> list;
    List<String> stringList;
    SwipeRecylerViewAdapter swipeRecylerViewAdapter;
    Favourite_Chore_Model favourite_chore_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

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
}
