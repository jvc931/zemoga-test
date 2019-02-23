package com.jvc.jsonplaceholderposts.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvc.jsonplaceholderposts.BaseApplication;
import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.Presenters.FavoritesPresenter;
import com.jvc.jsonplaceholderposts.ui.adapters.PostListAdapter;
import com.jvc.jsonplaceholderposts.ui.decorators.Decoration;
import com.jvc.jsonplaceholderposts.ui.interfaces.FavoritesViewInterface;
import com.jvc.jsonplaceholderposts.ui.interfaces.UserActionInterface;
import com.jvc.jsonplaceholderposts.ui.interfaces.UserIteractionsInterface;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * View that will show the list of the post selected like favorites.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class FavoritesFragment extends Fragment implements FavoritesViewInterface, UserIteractionsInterface {

    @Inject
    FavoritesPresenter presenter;

    private RecyclerView favoritePostRecycler;
    private PostListAdapter postListAdapter;
    private UserActionInterface userActionInterface;


    /**
     * New instance of the {@link FavoritesFragment}.
     *
     * @return new {@link FavoritesFragment} instance.
     */
    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((BaseApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        if (getActivity() instanceof UserActionInterface) {
            userActionInterface = (UserActionInterface) getActivity();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritePostRecycler = view.findViewById(R.id.recyclerview_favorite_post_list);
        favoritePostRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        favoritePostRecycler.setItemAnimator(new DefaultItemAnimator());
        favoritePostRecycler.addItemDecoration(new Decoration(view.getContext(), Decoration.VERTICAL_LIST));
    }

    @Override
    public void setFavoritePostList(RealmResults<Post> posts) {
        postListAdapter = new PostListAdapter(posts, this);
        favoritePostRecycler.setAdapter(postListAdapter);
    }

    @Override
    public void userClick(int postId) {
        userActionInterface.postSelected(postId);
    }

    @Override
    public void userDeleteSwipe(int postId) {
        //Nothing to do.
    }

    public void updateUi() {
        postListAdapter.notifyDataSetChanged();
    }
}
