package com.jvc.jsonplaceholderposts.data.service;

import com.jvc.jsonplaceholderposts.data.model.Comment;
import com.jvc.jsonplaceholderposts.data.model.Post;
import com.jvc.jsonplaceholderposts.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Contains all the webservice calls.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public interface JsonPlaceholderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("comments")
    Call<List<Comment>> getComments();

    @GET("users")
    Call<List<User>> getUsers();
}
