package com.jvc.jsonplaceholderposts.ui.interfaces;


import com.jvc.jsonplaceholderposts.data.model.Comment;
import com.jvc.jsonplaceholderposts.ui.BaseView;
import com.jvc.jsonplaceholderposts.ui.Presenters.PostPresenter;

import java.util.List;

/**
 * View contract that expose the methods that the {@link PostPresenter} can use.
 * Created by Jonathan Vargas on 22/02/2019.
 */
public interface PostDetailsViewInterface extends BaseView {

    void setPostDescription(String postDescription);

    void setUserName(String userName);

    void setUserUsername(String userUsername);

    void setUserPhone(String userPhone);

    void setUserEmail(String userEmail);

    void setUserWebsite(String userWebsite);

    void setCommentList(List<Comment> comments);
}
