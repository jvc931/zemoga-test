package com.jvc.jsonplaceholderposts.data.interactors;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jvc.jsonplaceholderposts.data.db.LiveRealmResults;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.data.service.JsonPlaceholderApi;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class FetchPostsFromServiceInteractor {

    private JsonPlaceholderApi apiClient;
    private Realm db;

    @Inject
    public FetchPostsFromServiceInteractor(JsonPlaceholderApi apiClient, Realm db){
        this.apiClient = apiClient;
        this.db = db;
    }

    /**
     * Gets and saves on the database the post getting from the service.
     * @return List of posts.
     */
    public LiveData<List<Post>> execute(){
        Call<List<Post>> call = apiClient.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                db.beginTransaction();
                db.copyToRealmOrUpdate(response.body());
                db.commitTransaction();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        return new LiveRealmResults<>(db.where(Post.class).findAll());
    }
}
