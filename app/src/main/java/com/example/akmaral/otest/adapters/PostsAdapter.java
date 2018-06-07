package com.example.akmaral.otest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akmaral.otest.CommentsActivity;
import com.example.akmaral.otest.models.Post;
import com.example.akmaral.otest.R;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    List<Post> posts;
    Context context;


    public PostsAdapter(List<Post> posts, Context context) {
        this.context = context;
        this.posts = posts;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
         return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {


        final Post post = posts.get(position);
        holder.textView.setText(post.getTitle());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentsActivity.class);
                intent.putExtra("postId", post.getId());
                context.startActivity(intent);

            }
        });



    }



    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        public View container;
        public ViewHolder(View v) {
            super(v);
            container = v;
            textView = (TextView) v.findViewById(R.id.post);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
