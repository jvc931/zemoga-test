package com.jvc.jsonplaceholderposts.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvc.jsonplaceholderposts.BaseApplication;
import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.Presenters.PostPresenter;
import com.jvc.jsonplaceholderposts.ui.adapters.PostListAdapter;
import com.jvc.jsonplaceholderposts.ui.decorators.Decoration;
import com.jvc.jsonplaceholderposts.ui.decorators.SwipeToDeleteCallback;
import com.jvc.jsonplaceholderposts.ui.interfaces.PostViewInterface;
import com.jvc.jsonplaceholderposts.ui.interfaces.UserActionInterface;
import com.jvc.jsonplaceholderposts.ui.interfaces.UserIteractionsInterface;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * View that will show the list of the posts.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class PostFragment extends Fragment implements PostViewInterface, UserIteractionsInterface {

    @Inject
    PostPresenter presenter;

    private RecyclerView postListRecycler;
    private PostListAdapter postListAdapter;
    private UserActionInterface userActionInterface;

    /**
     * New instance of the {@link PostFragment}.
     *
     * @return new {@link PostFragment} instance.
     */
    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((BaseApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
        return inflater.inflate(R.layout.fragment_post, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof UserActionInterface) {
            userActionInterface = (UserActionInterface) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postListRecycler = view.findViewById(R.id.recyclerview_post_list);
        postListRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        postListRecycler.setItemAnimator(new DefaultItemAnimator());
        postListRecycler.addItemDecoration(new Decoration(view.getContext(), Decoration.VERTICAL_LIST));
    }

    @Override
    public void setPostsFromService() {
        presenter.getPostsFromServiceInteractor().execute().observe(this, posts -> {
            if (posts != null) {
                setPostListAdapter(posts);
            }
        });
    }

    @Override
    public void setPostsFromDataBase(RealmResults<Post> posts) {
        setPostListAdapter(posts);
    }

    @Override
    public void userClick(int postId) {
        userActionInterface.postSelected(postId);
    }

    @Override
    public void userDeleteSwipe(int position) {
        presenter.deletePostById(postListAdapter.getPostIdSelected(position));
        postListAdapter.notifyItemRemoved(position);
    }

    private void setPostListAdapter(List<Post> posts) {
        postListAdapter = new PostListAdapter(posts, this);
        postListRecycler.setAdapter(postListAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(postListAdapter));
        itemTouchHelper.attachToRecyclerView(postListRecycler);
    }

    public void updateUi() {
        postListAdapter.notifyDataSetChanged();
    }
}
