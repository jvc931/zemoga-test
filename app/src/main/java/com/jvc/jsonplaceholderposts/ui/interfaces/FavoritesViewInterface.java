package com.jvc.jsonplaceholderposts.ui.interfaces;

import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.BaseView;
import com.jvc.jsonplaceholderposts.ui.Presenters.FavoritesPresenter;

import io.realm.RealmResults;

/**
 * View contract that expose the methods that the {@link FavoritesPresenter} can use.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public interface FavoritesViewInterface extends BaseView {

    void setFavoritePostList(RealmResults<Post> posts);
}
