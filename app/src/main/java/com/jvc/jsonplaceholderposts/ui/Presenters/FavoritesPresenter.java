package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.FetchFavoritePostsInteractor;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.fragments.FavoritesFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Presenter of the {@link FavoritesFragment}.
 * Created by Jonathan Vargas on 21/02/2019.
 */
@Singleton
public class FavoritesPresenter extends BasePresenter<FavoritesFragment> {

    private Realm db;
    private FetchFavoritePostsInteractor interactor;

    @Inject
    public FavoritesPresenter(Realm db, FetchFavoritePostsInteractor interactor) {
        this.db = db;
        this.interactor = interactor;
    }

    @Override
    public void attachView(FavoritesFragment view) {
        super.attachView(view);
        setFavoritePostList();
    }

    /**
     * Sets the favorite list of post to the view.
     */
    public void setFavoritePostList() {
        view.setFavoritePostList(interactor.execute());
    }
}
