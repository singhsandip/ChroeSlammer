package com.omninos.chroeslammer.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.chroeslammer.Activities.PostList;
import com.omninos.chroeslammer.Activities.Project_Details;
import com.omninos.chroeslammer.Helper.Constants;
import com.omninos.chroeslammer.Helper.SharedPrefManager;
import com.omninos.chroeslammer.Models.JobList;
import com.omninos.chroeslammer.Models.Notification_Model;
import com.omninos.chroeslammer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sandeep on 12-05-2017.
 */

public class PostList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Activity mActivity;
    List<JobList> list;
    LayoutInflater inflater;

    public PostList_Adapter(Activity mActivity, List<JobList> list)
    {
        this.mActivity = mActivity;
        this.list = list;
        inflater = LayoutInflater.from(mActivity);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_post_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PostList_Adapter.MyViewHolder)
        {
            PostList_Adapter.MyViewHolder myViewHolder = (PostList_Adapter.MyViewHolder) holder;

            String image = list.get(position).getImage();
            Picasso.with(mActivity).load(list.get(position).getImage().toString()).into(myViewHolder.job_image);
            myViewHolder.workername.setText(list.get(position).getUsername().toString());
            myViewHolder.jobtitle.setText(list.get(position).getTitle());
            myViewHolder.description.setText(list.get(position).getDescription());


        }

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public void addList(List<JobList> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView workername,jobtitle,description;
        ImageView job_image;
        public MyViewHolder(View itemView) {
            super(itemView);

            workername = (TextView)itemView.findViewById(R.id.worker_name_tv);
            jobtitle = (TextView)itemView.findViewById(R.id.job_title_tv);
            description = (TextView)itemView.findViewById(R.id.job_description);
            job_image = (ImageView)itemView.findViewById(R.id.job_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity,Project_Details.class);
              /*      String jotitle = list.get(getAdapterPosition()).getTitle();
                    SharedPrefManager.getInstance(mActivity).setJobTittle(jotitle);
                    String cat = list.get(getAdapterPosition()).getCategory();
                    SharedPrefManager.getInstance(mActivity).setCategory(cat);
                    String nm = list.get(getAdapterPosition()).getUsername();
                    SharedPrefManager.getInstance(mActivity).setUserName(nm);
                    String image = list.get(getAdapterPosition()).getImage();
                    SharedPrefManager.getInstance(mActivity).setIMage(image);
                    String datecreated = list.get(getAdapterPosition()).getCreated();
                    SharedPrefManager.getInstance(mActivity).setCreatedDate(datecreated);*/

                    intent.putExtra(Constants.JOBTITLE,list.get(getAdapterPosition()).getTitle());
                    Log.d("titl",list.get(getAdapterPosition()).getTitle());
                    intent.putExtra(Constants.CATEGORY,list.get(getAdapterPosition()).getCategory());
                    Log.d("titl",list.get(getAdapterPosition()).getCategory());
                    intent.putExtra(Constants.USERNAME,list.get(getAdapterPosition()).getUsername());
                    Log.d("titl",list.get(getAdapterPosition()).getUsername());
                    intent.putExtra(Constants.CREATED_DATE,list.get(getAdapterPosition()).getCreated());
                    Log.d("titl",list.get(getAdapterPosition()).getCreated());
                    intent.putExtra(Constants.DESCRIPTION,list.get(getAdapterPosition()).getDescription());
                    Log.d("titl",list.get(getAdapterPosition()).getDescription());
                    intent.putExtra(Constants.IMAGE,list.get(getAdapterPosition()).getImage());
                    Log.d("titl",list.get(getAdapterPosition()).getImage());
                    intent.putExtra(Constants.PHONE,list.get(getAdapterPosition()).getPhone());
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}
