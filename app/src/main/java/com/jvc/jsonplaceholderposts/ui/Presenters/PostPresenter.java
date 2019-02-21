package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.FetchPostsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.fragments.PostFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Presenter of the {@link PostFragment}.
 * Created by Jonathan Vargas on 21/02/2019.
 */
@Singleton
public class PostPresenter extends BasePresenter<PostFragment> {

    private FetchPostsFromServiceInteractor interactor;
    private Realm db;

    @Inject
    public PostPresenter(FetchPostsFromServiceInteractor interactor, Realm db) {
        this.interactor = interactor;
        this.db = db;
    }

    public FetchPostsFromServiceInteractor getInteractor() {
        return interactor;
    }

    @Override
    public void attachView(PostFragment view) {
        super.attachView(view);
        setPostList();
    }

    private void setPostList(){
        RealmResults posts = db.where(Post.class).findAll();
        if(posts.isEmpty()){
            view.setPostsFromService();
        } else {
            view.setPostsFromDataBase(posts);
        }
    }
}
