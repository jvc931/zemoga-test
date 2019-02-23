package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.User;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Jonathan Vargas on 22/02/2019.
 */
public class FetchUserByIdInteractor {

    private Realm db;

    @Inject
    public FetchUserByIdInteractor(Realm db) {
        this.db = db;
    }

    /**
     * Gets the user from the database filter by id.
     *
     * @param userId user id.
     * @return user object.
     */
    public User execute(int userId) {
        return db.where(User.class).equalTo("id", userId).findFirst();
    }
}
