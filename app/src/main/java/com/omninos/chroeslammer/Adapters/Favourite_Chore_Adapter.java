package com.omninos.chroeslammer.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omninos.chroeslammer.Models.Favourite_Chore_Model;
import com.omninos.chroeslammer.Models.Notification_Model;
import com.omninos.chroeslammer.R;

import java.util.List;

/**
 * Created by sandeep on 12-05-2017.
 */

public class Favourite_Chore_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    Activity mActivity;
    List<Favourite_Chore_Model> list;
    LayoutInflater inflater;



    public Favourite_Chore_Adapter(Activity mActivity, List<Favourite_Chore_Model> list)
    {
        this.mActivity = mActivity;
        this.list = list;
        inflater = LayoutInflater.from(mActivity);

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_favourite_chore,parent,false);

        return new MyViewHolderFavoutriteChore(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NotificationAdapter.MyViewHolder)
        {
            NotificationAdapter.MyViewHolder myViewHolder = (NotificationAdapter.MyViewHolder) holder;

            myViewHolder.name.setText(list.get(position).getName());
            myViewHolder.time.setText(list.get(position).getTime());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolderFavoutriteChore extends RecyclerView.ViewHolder {

        TextView name,time,text;
        public MyViewHolderFavoutriteChore(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.fav_chore_name_tv);
            time = (TextView)itemView.findViewById(R.id.fav_chore_time_tv);
            text = (TextView)itemView.findViewById(R.id.fav_chore_tv);
        }
    }
}
