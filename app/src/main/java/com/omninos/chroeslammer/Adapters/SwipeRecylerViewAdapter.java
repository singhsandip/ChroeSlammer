package com.omninos.chroeslammer.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.omninos.chroeslammer.Activities.Extra;
import com.omninos.chroeslammer.Models.Favourite_Chore_Model;
import com.omninos.chroeslammer.R;

import java.util.List;

/**
 * Created by sandeep on 12-05-2017.
 */

public class SwipeRecylerViewAdapter extends RecyclerSwipeAdapter<SwipeRecylerViewAdapter.ViewHolder> {

    private Activity activity;
    private List<Favourite_Chore_Model> list;
    LayoutInflater inflater;


    public SwipeRecylerViewAdapter(Activity activity, List<Favourite_Chore_Model> list)
    {
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_favourite_chore,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


       // viewHolder.name.setText(list.get(position).getName());
        //viewHolder.time.setText(list.get(position).getTime());

        viewHolder.close_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });




        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag From Left
       // viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        // Drag From Right
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));


        // Handling different events when swiping
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        ImageView close_image_view;
        TextView tvDelete,name,time,text,tvEdit,tvShare;


        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);


            name = (TextView)itemView.findViewById(R.id.fav_chore_name_tv);
            time = (TextView)itemView.findViewById(R.id.fav_chore_time_tv);
            text = (TextView)itemView.findViewById(R.id.fav_chore_tv);
            close_image_view = (ImageView)itemView.findViewById(R.id.close_image_view);
        }
    }
}
