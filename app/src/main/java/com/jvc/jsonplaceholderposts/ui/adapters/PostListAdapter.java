package com.jvc.jsonplaceholderposts.ui.adapters;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Post;

import java.util.List;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {
    private List<Post> posts;

    public PostListAdapter(List<Post> posts){
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostListAdapter.PostListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PostListViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.PostListViewHolder postListHolder, int position) {
        Post post = posts.get(position);

        if (post.isRead() || position > 20){
            postListHolder.readIndicatorView.setBackgroundResource(R.drawable.circle_shape_without_background);
        } else {
            postListHolder.readIndicatorView.setBackgroundResource(R.drawable.circle_shape_with_background);
        }

        postListHolder.postTitleText.setText(post.getTitle());
        postListHolder.favoriteIcon.setVisibility(post.isFavorite() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostListViewHolder extends RecyclerView.ViewHolder {
        private final View readIndicatorView;
        private final TextView postTitleText;
        private final ImageView favoriteIcon;

        public PostListViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false));
            readIndicatorView = itemView.findViewById(R.id.view_post_list_item_read_indicator);
            postTitleText = itemView.findViewById(R.id.textview_post_list_item_title);
            favoriteIcon = itemView.findViewById(R.id.imageView_post_list_item_favorites_icon);
        }
    }
}
