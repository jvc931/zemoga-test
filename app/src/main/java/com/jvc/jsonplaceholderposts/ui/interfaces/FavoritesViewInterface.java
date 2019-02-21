package com.jvc.jsonplaceholderposts.ui.interfaces;

import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.BaseView;

import io.realm.RealmResults;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public interface FavoritesViewInterface extends BaseView {

    void setFavoritePostList(RealmResults<Post> posts);
}
