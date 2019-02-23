package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Post;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jonathan Vargas on 22/02/2019.
 */
public class FetchFavoritePostsInteractor {

    private Realm db;

    @Inject
    public FetchFavoritePostsInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Gets all the post selected like favorite.
     *
     * @return list of favorite posts.
     */
    public RealmResults<Post> execute() {
        return db.where(Post.class).equalTo("favorite", true).findAll();
    }

}
