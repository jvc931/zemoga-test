package com.jvc.jsonplaceholderposts.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.jvc.jsonplaceholderposts.BaseApplication;
import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.ui.BaseView;
import com.jvc.jsonplaceholderposts.ui.Presenters.MainPostPresenter;
import com.jvc.jsonplaceholderposts.ui.fragments.FavoritesFragment;
import com.jvc.jsonplaceholderposts.ui.fragments.PostFragment;

import javax.inject.Inject;

/**
 * Main view that manages the transitions between views.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class PostActivity extends AppCompatActivity implements BaseView {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;

    private static final int NUMBER_OF_TABS = 2;

    @Inject
    MainPostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ((BaseApplication) getApplication()).getApplicationComponent().inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout_post_activity);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        FloatingActionButton deletePostsButton =  findViewById(R.id.floating_button_delete_posts);
        deletePostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           switch (position){
               case 1:
                   return FavoritesFragment.newInstance();
               case 0:
               default:
                   return PostFragment.newInstance();
           }
        }

        @Override
        public int getCount() {
            return NUMBER_OF_TABS;
        }
    }
}
