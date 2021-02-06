package com.example.test.onboardingscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter1 extends RecyclerView.Adapter<PostAdapter1.ViewHolder> {

    Context context;
    ArrayList<PostModel> postModels;

    public PostAdapter1(Context context, ArrayList<PostModel> postModels) {
        this.context = context;
        this.postModels = postModels;
    }

    @NonNull
    @Override
    public PostAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.postlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter1.ViewHolder holder, final int position) {

        if(postModels.get(position).getCaption() == null){
            holder.tv1.setVisibility(View.GONE);
        }
        else
            holder.tv1.setText(postModels.get(position).getCaption());
        Glide.with(context).load(postModels.get(position).getImage()).into(holder.iv1);


    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv1;
        TextView tv1;
        public ViewHolder(View v) {
            super(v);

            iv1 = v.findViewById(R.id.image_post);
            tv1 = v.findViewById(R.id.caption);

        }
    }
}
