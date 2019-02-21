package com.jvc.jsonplaceholderposts.ui.interfaces;

import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.ui.BaseView;

import io.realm.RealmResults;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public interface PostViewInterface extends BaseView {

    /**
     * Sets and saves on the database the post getting from the service.
     */
    void setPostsFromService();

    /**
     * Sets the post list with the database data.
     * @param posts
     */
    void setPostsFromDataBase(RealmResults<Post> posts);
}
