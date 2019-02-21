package com.jvc.jsonplaceholderposts.ui.interfaces;

import com.jvc.jsonplaceholderposts.ui.BaseView;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public interface PostViewInterface extends BaseView {

    /**
     * Gets and saves on the database the post getting from the service.
     */
    void setPostsFromService();

    /**
     * Gets and saves on the database the comments getting from the service.
     */
    void setCommentsFromService();
}
