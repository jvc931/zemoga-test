package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.Comment;
import com.jvc.jsonplaceholderposts.data.service.JsonPlaceholderApi;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class FetchCommentsFromServiceInteractor {

    private JsonPlaceholderApi apiClient;
    private Realm db;

    @Inject
    public FetchCommentsFromServiceInteractor(JsonPlaceholderApi apiClient, Realm db) {
        this.apiClient = apiClient;
        this.db = db;
    }

    /**
     * Gets and saves on the database the comments getting from the service.
     */
    public void execute() {
        if (db.where(Comment.class).count() == 0) {
            Call<List<Comment>> call = apiClient.getComments();
            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    db.beginTransaction();
                    db.copyToRealmOrUpdate(response.body());
                    db.commitTransaction();
                    db.close();
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {

                }
            });
        }
    }
}
