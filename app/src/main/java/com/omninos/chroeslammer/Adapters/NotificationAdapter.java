package com.omninos.chroeslammer.Adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omninos.chroeslammer.Models.Notification_Model;
import com.omninos.chroeslammer.R;

import java.util.List;

/**
 * Created by SamarOmninos on 11/05/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity mActivity;
    List<Notification_Model> list;
    LayoutInflater inflater;

    public NotificationAdapter(Activity mActivity, List<Notification_Model> list)
    {
        this.mActivity = mActivity;
        this.list = list;
        inflater = LayoutInflater.from(mActivity);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_notification,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof  MyViewHolder)
        {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

          //  myViewHolder.name.setText("fgdasas");
           // myViewHolder.time.setText("fgdasas");

         /*   Typeface custon_font =  Typeface.createFromAsset(mActivity.getAssets(),"fonts/myfont.ttf");
           // t.setTypeface(custon_font);

            myViewHolder.time.setTypeface(custon_font);*/
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       Typeface custon_font =  Typeface.createFromAsset(mActivity.getAssets(),"OpenSans-Light.ttf");

        TextView name,time;
        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name_tv);
            time = (TextView)itemView.findViewById(R.id.time_tv);


            // t.setTypeface(custon_font);

           time.setTypeface(custon_font);
        }
    }
}
