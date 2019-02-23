package com.jvc.jsonplaceholderposts.data.interactors;

import com.jvc.jsonplaceholderposts.data.model.User;
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
public class FetchUsersFromServiceInteractor {

    private JsonPlaceholderApi apiClient;
    private Realm db;

    @Inject
    public FetchUsersFromServiceInteractor(JsonPlaceholderApi apiClient, Realm db) {
        this.apiClient = apiClient;
        this.db = db;
    }

    /**
     * Gets and saves on the database the users getting from the service.
     */
    public void execute() {
        if (db.where(User.class).count() == 0) {
            Call<List<User>> call = apiClient.getUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    db.beginTransaction();
                    db.copyToRealmOrUpdate(response.body());
                    db.commitTransaction();
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
        }
    }
}
