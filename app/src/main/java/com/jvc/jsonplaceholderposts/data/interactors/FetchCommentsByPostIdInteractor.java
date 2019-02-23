package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Comment;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jonathan Vargas on 22/02/2019.
 */
public class FetchCommentsByPostIdInteractor {

    private Realm db;

    @Inject
    public FetchCommentsByPostIdInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Gets the comments from the database filter by postId.
     *
     * @param postId postId to filter.
     * @return list of comments of the post.
     */
    public RealmResults<Comment> execute(int postId) {
        return db.where(Comment.class).equalTo("postId", postId).findAll();
    }
}
