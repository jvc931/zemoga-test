package com.jvc.jsonplaceholderposts.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvc.jsonplaceholderposts.BaseApplication;
import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Comment;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.BaseView;
import com.jvc.jsonplaceholderposts.ui.Presenters.PostPresenter;
import com.jvc.jsonplaceholderposts.ui.interfaces.PostViewInterface;

import java.util.List;

import javax.inject.Inject;

/**
 * View that will show the list of the posts.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class PostFragment extends Fragment implements PostViewInterface {

    @Inject
    PostPresenter presenter;

    private List<Post> posts;
    private List<Comment> comments;

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
        ((BaseApplication) getActivity().getApplication()).getApplicationComponent().inject(this);;
        return inflater.inflate(R.layout.fragment_post, container, false);
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
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void setPostsFromService() {
        presenter.getPostsInteractor().execute().observe(this, posts ->{
            if (posts != null){
                this.posts = posts;
            }
        });
    }

    @Override
    public void setCommentsFromService() {
        presenter.getCommentsInteractor().execute().observe(this, comments ->{
            if (comments != null){
                this.comments = comments;
            }
        });
    }
}
