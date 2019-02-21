package com.jvc.jsonplaceholderposts.di;

import com.jvc.jsonplaceholderposts.BuildConfig;
import com.jvc.jsonplaceholderposts.data.service.JsonPlaceholderApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jonathan Vargas on 20/02/2019.
 */

@Module
public class ApplicationModule {

    /**
     * Returns a unique reference of retrofit.
     *
     * @return retrofit instance
     */
    @Provides
    @Singleton
    JsonPlaceholderApi provideJsonPlaceholderApi() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retrofit.create(JsonPlaceholderApi.class);
    }

    @Provides
    @Singleton
    Realm provideRealmDb(){
        return Realm.getDefaultInstance();
    }
}
