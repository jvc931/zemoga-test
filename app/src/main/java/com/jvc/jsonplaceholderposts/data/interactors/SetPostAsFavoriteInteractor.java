package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Post;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Jonathan Vargas on 23/02/2019.
 */
public class SetPostAsFavoriteInteractor {

    private Realm db;

    @Inject
    public SetPostAsFavoriteInteractor(Realm db) {
        this.db = db;
    }

    public void execute(Post post) {
        db.beginTransaction();
        post.setFavorite(true);
        db.copyToRealmOrUpdate(post);
        db.commitTransaction();
    }
}
