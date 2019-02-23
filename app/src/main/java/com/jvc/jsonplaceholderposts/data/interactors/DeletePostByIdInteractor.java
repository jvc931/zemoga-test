package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Post;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Jonathan Vargas on 23/02/2019.
 */
public class DeletePostByIdInteractor {

    private Realm db;

    @Inject
    public DeletePostByIdInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Delete the post filter by id.
     *
     * @param postId id of the post to delete.
     */
    public void execute(int postId) {
        db.beginTransaction();
        db.where(Post.class).equalTo("id", postId).findFirst().deleteFromRealm();
        db.commitTransaction();
    }
}
