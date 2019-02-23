package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Post;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Jonathan Vargas on 23/02/2019.
 */
public class DeletePostInteractor {

    private Realm db;

    @Inject
    public DeletePostInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Delete all post from the database.
     */
    public void execute() {
        db.beginTransaction();
        db.delete(Post.class);
        db.commitTransaction();
    }
}
