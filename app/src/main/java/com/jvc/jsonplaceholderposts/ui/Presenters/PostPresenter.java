package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.FetchCommentsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchPostsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.ui.fragments.PostFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Presenter of the {@link PostFragment}.
 * Created by Jonathan Vargas on 21/02/2019.
 */
@Singleton
public class PostPresenter extends BasePresenter<PostFragment> {

    private FetchPostsFromServiceInteractor postsInteractor;
    private FetchCommentsFromServiceInteractor commentsInteractor;

    @Inject
    public PostPresenter(FetchPostsFromServiceInteractor postsInteractor, FetchCommentsFromServiceInteractor commentsInteractor) {
        this.postsInteractor = postsInteractor;
        this.commentsInteractor = commentsInteractor;
    }

    public FetchPostsFromServiceInteractor getPostsInteractor() {
        return postsInteractor;
    }

    public FetchCommentsFromServiceInteractor getCommentsInteractor(){
        return commentsInteractor;
    }
}
