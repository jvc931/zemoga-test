package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.FetchCommentsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchUsersFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.model.User;
import com.jvc.jsonplaceholderposts.ui.activities.PostActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

/**
 * Presenter for the {@link PostActivity}.
 * Created by Jonathan Vargas on 21/02/2019.
 */
@Singleton
public class MainPostPresenter extends BasePresenter<PostActivity> {

    private FetchCommentsFromServiceInteractor commentsFromServiceInteractor;
    private FetchUsersFromServiceInteractor usersFromServiceInteractor;
    private Realm db;

    @Inject
    public MainPostPresenter(FetchUsersFromServiceInteractor usersFromServiceInteractor, FetchCommentsFromServiceInteractor commentsFromServiceInteractor, Realm db) {
        this.commentsFromServiceInteractor = commentsFromServiceInteractor;
        this.usersFromServiceInteractor = usersFromServiceInteractor;
        this.db = db;
    }

    @Override
    public void attachView(PostActivity view) {
        super.attachView(view);
        saveUsers();
        saveComments();
    }

    private void saveUsers(){
        if (db.where(User.class).findAll().isEmpty()){
            usersFromServiceInteractor.execute();
        }
    }

    private void saveComments(){
        if (db.where(User.class).findAll().isEmpty()){
            commentsFromServiceInteractor.execute();
        }
    }
}
