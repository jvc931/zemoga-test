package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.FetchCommentsByPostIdInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchPostByIdInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchUserByIdInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.SetPostAsFavoriteInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.SetPostReadInteractor;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.data.model.User;
import com.jvc.jsonplaceholderposts.ui.activities.PostDetailsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Jonathan Vargas on 22/02/2019.
 */
@Singleton
public class PostDetailsPresenter extends BasePresenter<PostDetailsActivity> {

    private FetchUserByIdInteractor fetchUserByIdInteractor;
    private FetchCommentsByPostIdInteractor fetchCommentsByPostIdInteractor;
    private FetchPostByIdInteractor fetchPostByIdInteractor;
    private SetPostAsFavoriteInteractor setPostAsFavoriteInteractor;
    private SetPostReadInteractor setPostReadInteractor;
    private int postId;
    private Post post;

    @Inject
    public PostDetailsPresenter(FetchUserByIdInteractor fetchUserByIdInteractor,
                                FetchCommentsByPostIdInteractor fetchCommentsByPostIdInteractor,
                                FetchPostByIdInteractor fetchPostByIdInteractor,
                                SetPostReadInteractor setPostReadInteractor,
                                SetPostAsFavoriteInteractor setPostAsFavoriteInteractor) {
        this.fetchCommentsByPostIdInteractor = fetchCommentsByPostIdInteractor;
        this.fetchPostByIdInteractor = fetchPostByIdInteractor;
        this.fetchUserByIdInteractor = fetchUserByIdInteractor;
        this.setPostAsFavoriteInteractor = setPostAsFavoriteInteractor;
        this.setPostReadInteractor = setPostReadInteractor;
    }

    public void setPostId(int postId) {
        this.postId = postId;
        setUi();
        updatePostAsRead();
    }

    @Override
    public void attachView(PostDetailsActivity view) {
        super.attachView(view);
    }

    private void setUi(){
        post = fetchPostByIdInteractor.execute(postId);
        User user = fetchUserByIdInteractor.execute(post.getUserId());
        view.setPostDescription(post.getBody());
        view.setUserName(user.getName());
        view.setUserUsername(user.getUsername());
        view.setUserEmail(user.getEmail());
        view.setUserPhone(user.getPhone());
        view.setUserWebsite(user.getWebsite());
        view.setCommentList(fetchCommentsByPostIdInteractor.execute(postId));
    }

    private void updatePostAsRead(){
        if (!post.isRead()){
            setPostReadInteractor.execute(post);
        }
    }

    public void updatePostAsFavorite(){
        if (!post.isFavorite()) {
            setPostAsFavoriteInteractor.execute(post);
        }
    }
}
