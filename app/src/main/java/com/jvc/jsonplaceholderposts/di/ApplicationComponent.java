package com.jvc.jsonplaceholderposts.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jonathan Vargas on 20/02/2019.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
}
