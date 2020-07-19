package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class adaptermodel extends  RecyclerView.Adapter<viewholder>{

    List<data> modelview ;
    String heading,url,title,date,time,web;


    public adaptermodel(List<data> model) {
        this.modelview = model;

    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        heading = modelview.get(position).getHeading();
        title = modelview.get(position).getTitle();
        url = modelview.get(position).getThumbnail();
        date = modelview.get(position).getDate();
        time = modelview.get(position).getTime();
        web = modelview.get(position).getUrl();
        holder.setdata(heading,url,title,date,time,position,web);
    }



    @Override
    public int getItemCount() {
        return modelview.size();
    }
}

class viewholder extends RecyclerView.ViewHolder {

    TextView heading;
    TextView title;
    TextView date;
    TextView time;
    ImageView thumbnail;
    ConstraintLayout l ;

    Button edit, delete;


    public viewholder(@NonNull View itemView) {
        super(itemView);
        heading = itemView.findViewById(R.id.heading);
        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);
        time = itemView.findViewById(R.id.time);
        thumbnail = itemView.findViewById(R.id.imageView);
        l = itemView.findViewById(R.id.consti);
    }

    public void setdata(String head, String url, String titl, String dat, String tim, final int position, final String web) {
        heading.setText(head);
        title.setText(titl);
        date.setText(dat);
        time.setText(tim);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(itemView.getContext()).load(url).apply(options).into(thumbnail);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(itemView.getContext(),second.class);
                k.putExtra("url",web);
                itemView.getContext().startActivity(k);
            }
        });

    }
}
