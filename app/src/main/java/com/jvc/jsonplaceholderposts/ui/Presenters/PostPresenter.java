package com.jvc.jsonplaceholderposts.ui.Presenters;

import com.jvc.jsonplaceholderposts.data.interactors.DeletePostByIdInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchPostsFromServiceInteractor;
import com.jvc.jsonplaceholderposts.data.interactors.FetchPostsInteractor;
import com.jvc.jsonplaceholderposts.ui.fragments.PostFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmResults;

/**
 * Presenter of the {@link PostFragment}.
 * Created by Jonathan Vargas on 21/02/2019.
 */
@Singleton
public class PostPresenter extends BasePresenter<PostFragment> {

    private FetchPostsFromServiceInteractor postsFromServiceInteractor;
    private FetchPostsInteractor fetchPostsInteractor;
    private DeletePostByIdInteractor deletePostByIdInteractor;

    @Inject
    public PostPresenter(FetchPostsFromServiceInteractor fetchPostsFromServiceInteractor,
                         FetchPostsInteractor fetchPostsInteractor,
                         DeletePostByIdInteractor deletePostByIdInteractor) {
        this.postsFromServiceInteractor = fetchPostsFromServiceInteractor;
        this.fetchPostsInteractor = fetchPostsInteractor;
        this.deletePostByIdInteractor = deletePostByIdInteractor;
    }

    public FetchPostsFromServiceInteractor getPostsFromServiceInteractor() {
        return postsFromServiceInteractor;
    }

    @Override
    public void attachView(PostFragment view) {
        super.attachView(view);
        setPostList();
    }

    private void setPostList() {
        RealmResults posts = fetchPostsInteractor.execute();
        if (posts.isEmpty()) {
            view.setPostsFromService();
        } else {
            view.setPostsFromDataBase(posts);
        }
    }

    public void deletePostById(int postId) {
        deletePostByIdInteractor.execute(postId);
    }
}
