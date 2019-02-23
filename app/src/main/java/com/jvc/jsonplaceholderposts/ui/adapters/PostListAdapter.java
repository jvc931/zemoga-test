package com.jvc.jsonplaceholderposts.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.interfaces.UserIteractionsInterface;

import java.util.List;

/**
 * Adapter to manage the posts list.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {

    private List<Post> posts;
    private UserIteractionsInterface userIteractionsInterface;
    private static final int FIRST_UNREAD_POSTS = 20;

    public PostListAdapter(List<Post> posts, UserIteractionsInterface userIteractionsInterface) {
        this.posts = posts;
        this.userIteractionsInterface = userIteractionsInterface;
    }

    @NonNull
    @Override
    public PostListAdapter.PostListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PostListViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.PostListViewHolder postListHolder, int position) {
        postListHolder.blindPostData(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void deleteItem(int position) {
        userIteractionsInterface.userDeleteSwipe(position);
    }

    public int getPostIdSelected(int position) {
        return posts.get(position).getId();
    }

    public class PostListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View readIndicatorView;
        private final TextView postTitleText;
        private final ImageView favoriteIcon;
        private Post post;

        public PostListViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false));
            readIndicatorView = itemView.findViewById(R.id.view_post_list_item_read_indicator);
            postTitleText = itemView.findViewById(R.id.textview_post_list_item_title);
            favoriteIcon = itemView.findViewById(R.id.imageView_post_list_item_favorites_icon);
            itemView.setOnClickListener(this);
        }

        public void blindPostData(Post post) {
            this.post = post;
            if (post.isRead() || post.getId() > FIRST_UNREAD_POSTS) {
                readIndicatorView.setBackgroundResource(R.drawable.circle_shape_without_background);
            } else {
                readIndicatorView.setBackgroundResource(R.drawable.circle_shape_with_background);
            }

            postTitleText.setText(post.getTitle());
            favoriteIcon.setVisibility(post.isFavorite() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            userIteractionsInterface.userClick(post.getId());
        }
    }
}
