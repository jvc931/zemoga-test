package com.jvc.jsonplaceholderposts;

import android.app.Application;

import com.jvc.jsonplaceholderposts.di.ApplicationComponent;
import com.jvc.jsonplaceholderposts.di.DaggerApplicationComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Jonathan Vargas on 20/02/2019.
 */
public class BaseApplication extends Application {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("JSONPlaceholder.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
