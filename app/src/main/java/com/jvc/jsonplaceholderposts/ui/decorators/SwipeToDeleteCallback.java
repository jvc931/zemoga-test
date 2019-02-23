package com.jvc.jsonplaceholderposts.ui.decorators;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.jvc.jsonplaceholderposts.ui.adapters.PostListAdapter;

/**
 * Manage the swipe to delete user interaction.
 * Created by Jonathan Vargas on 23/02/2019.
 */
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private PostListAdapter postListAdapter;

    public SwipeToDeleteCallback(PostListAdapter postListAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.postListAdapter = postListAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        postListAdapter.deleteItem(viewHolder.getAdapterPosition());
    }
}
