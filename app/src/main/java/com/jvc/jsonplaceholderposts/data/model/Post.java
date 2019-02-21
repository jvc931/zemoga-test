package com.jvc.jsonplaceholderposts.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class Post extends RealmObject {
    @PrimaryKey
    private int id;
    private int userId;
    private String title;
    private String body;
    private boolean favorite;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

