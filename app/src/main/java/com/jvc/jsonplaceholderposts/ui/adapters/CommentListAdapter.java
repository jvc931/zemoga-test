package com.jvc.jsonplaceholderposts.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Comment;

import java.util.List;

/**
 * Adapter to manage the comments list.
 * Created by Jonathan Vargas on 23/02/2019.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentListViewHolder> {

    private List<Comment> comments;

    public CommentListAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentListAdapter.CommentListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new CommentListAdapter.CommentListViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.CommentListViewHolder commentListHolder, int position) {
        commentListHolder.blindPostData(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentListViewHolder extends RecyclerView.ViewHolder {
        private final TextView commentText;
        private Comment comment;

        public CommentListViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comment, parent, false));
            commentText = itemView.findViewById(R.id.textview_comment_list_text);
        }

        public void blindPostData(Comment comment) {
            this.comment = comment;
            commentText.setText(comment.getBody());
        }
    }
}
