package com.jvc.jsonplaceholderposts.di;

import com.jvc.jsonplaceholderposts.ui.activities.PostActivity;
import com.jvc.jsonplaceholderposts.ui.fragments.FavoritesFragment;
import com.jvc.jsonplaceholderposts.ui.fragments.PostFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jonathan Vargas on 20/02/2019.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    /**
     * Inject method for the {@link PostActivity}.
     *
     * @param activity {@link PostActivity} instance.
     */
    void inject(PostActivity activity);

    /**
     * Inject method for the {@link PostFragment}.
     *
     * @param fragment {@link PostFragment} instance.
     */
    void inject(PostFragment fragment);

    /**
     * Inject method for the {@link FavoritesFragment}.
     *
     * @param fragment {@link FavoritesFragment} instance.
     */
    void inject(FavoritesFragment fragment);

}
