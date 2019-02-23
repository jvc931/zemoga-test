package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Post;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Jonathan Vargas on 22/02/2019.
 */
public class FetchPostByIdInteractor {

    private Realm db;

    @Inject
    public FetchPostByIdInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Gets a post filter by postId.
     *
     * @param postId id to filter;
     * @return post object.
     */
    public Post execute(int postId) {
        return db.where(Post.class).equalTo("id", postId).findFirst();
    }

}
