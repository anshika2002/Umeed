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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<PostModel> postModels;

    public PostAdapter(Context context, ArrayList<PostModel> postModels) {
        this.context = context;
        this.postModels = postModels;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(postModels.get(position).getImage()).into(holder.iv1);
        holder.tv1.setVisibility(View.GONE);

        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FullPost.class);
                intent.putExtra("link",postModels.get(position).getImage());
                intent.putExtra("caption",postModels.get(position).getCaption());
                context.startActivity(intent);
            }
        });

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

            iv1 = v.findViewById(R.id.image_post1);
            tv1 = v.findViewById(R.id.caption1);

        }
    }
}
