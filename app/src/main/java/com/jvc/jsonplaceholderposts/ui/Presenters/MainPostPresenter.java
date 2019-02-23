package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.DeletePostInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchCommentsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchPostsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchUsersFromServiceInteractor;
import com.jvc.jsonplaceholderposts.ui.activities.PostActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Presenter for the {@link PostActivity}.
 * Created by Jonathan Vargas on 21/02/2019.
 */
@Singleton
public class MainPostPresenter extends BasePresenter<PostActivity> {

    private FetchCommentsFromServiceInteractor commentsFromServiceInteractor;
    private FetchUsersFromServiceInteractor usersFromServiceInteractor;
    private DeletePostInteractor deletePostInteractor;
    private FetchPostsFromServiceInteractor fetchPostsFromServiceInteractor;

    @Inject
    public MainPostPresenter(FetchUsersFromServiceInteractor usersFromServiceInteractor,
                             FetchCommentsFromServiceInteractor commentsFromServiceInteractor,
                             DeletePostInteractor deletePostInteractor,
                             FetchPostsFromServiceInteractor fetchPostsFromServiceInteractor) {
        this.commentsFromServiceInteractor = commentsFromServiceInteractor;
        this.usersFromServiceInteractor = usersFromServiceInteractor;
        this.deletePostInteractor = deletePostInteractor;
        this.fetchPostsFromServiceInteractor = fetchPostsFromServiceInteractor;
    }

    @Override
    public void attachView(PostActivity view) {
        super.attachView(view);
        saveUsers();
        saveComments();
    }

    private void saveUsers() {
        usersFromServiceInteractor.execute();
    }

    private void saveComments() {
        commentsFromServiceInteractor.execute();
    }

    public void deleteAllPost() {
        deletePostInteractor.execute();
    }

    public FetchPostsFromServiceInteractor getFetchPostsFromServiceInteractor() {
        return fetchPostsFromServiceInteractor;
    }
}
