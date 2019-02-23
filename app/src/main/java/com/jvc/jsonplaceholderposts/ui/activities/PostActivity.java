package com.jvc.jsonplaceholderposts.ui.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jvc.jsonplaceholderposts.BaseApplication;
import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.ui.BaseView;
import com.jvc.jsonplaceholderposts.ui.Presenters.MainPostPresenter;
import com.jvc.jsonplaceholderposts.ui.fragments.FavoritesFragment;
import com.jvc.jsonplaceholderposts.ui.fragments.PostFragment;
import com.jvc.jsonplaceholderposts.ui.interfaces.UserActionInterface;

import javax.inject.Inject;

/**
 * Main view that manages the transitions between views.
 * Created by Jonathan Vargas on 21/02/2019.
 */
public class PostActivity extends AppCompatActivity implements BaseView, UserActionInterface {

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
    private FavoritesFragment favoritesFragment;
    private PostFragment postFragment;

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

        FloatingActionButton deletePostsButton = findViewById(R.id.floating_button_delete_posts);
        deletePostsButton.setOnClickListener(view -> {
            presenter.deleteAllPost();
            postFragment.updateUi();
            favoritesFragment.updateUi();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_reload_post) {
            presenter.getFetchPostsFromServiceInteractor().execute().observe(this, posts -> {
                if (posts != null) {
                    postFragment.updateUi();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void postSelected(int postId) {
        Intent goToPostDetails = new Intent(this, PostDetailsActivity.class);
        goToPostDetails.putExtra("postSelected", postId);
        startActivity(goToPostDetails);
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
            switch (position) {
                case 1:
                    favoritesFragment = FavoritesFragment.newInstance();
                    return favoritesFragment;
                case 0:
                default:
                    postFragment = PostFragment.newInstance();
                    return postFragment;
            }
        }

        @Override
        public int getCount() {
            return NUMBER_OF_TABS;
        }
    }
}
