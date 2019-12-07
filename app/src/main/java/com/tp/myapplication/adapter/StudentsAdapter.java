package com.tp.myapplication.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tp.myapplication.R;
import com.tp.myapplication.data.Constants;
import com.tp.myapplication.model.User;
import com.tp.myapplication.ui.DetailsStudentActivity;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    Context context;
    private ArrayList<User> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public StudentsAdapter(Context context, ArrayList<User> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final User user = mData.get(position);
        if (user.isPresent()) {
            holder.tvStatus.setText("Présent");
        } else {
            holder.tvStatus.setText("Absent");
        }
        holder.tvName.setText(user.getFullName());
        Glide.with(context).load(user.getImage()).into(holder.imgPicture);
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "click student " + user.getFullName(), Toast.LENGTH_SHORT).show();
                startDetail(position);
            }
        });
    }

    private void startDetail(int position) {
        Intent detail = new Intent(context, DetailsStudentActivity.class);
        detail.putExtra(Constants.ARG_POSITION, position);
        context.startActivity(detail);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvStatus;
        ImageView imgPicture;
        LinearLayout llContainer;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStatus = itemView.findViewById(R.id.tv_status);
            imgPicture = itemView.findViewById(R.id.img_picture);
            llContainer = itemView.findViewById(R.id.ll_container);
        }


    }
}