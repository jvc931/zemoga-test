package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Post;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Jonathan Vargas on 23/02/2019.
 */
public class SetPostReadInteractor {

    private Realm db;

    @Inject
    public SetPostReadInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Set the post as read.
     *
     * @param post post to modify.
     */
    public void execute(Post post) {
        db.beginTransaction();
        post.setRead(true);
        db.copyToRealmOrUpdate(post);
        db.commitTransaction();
    }
}
