package com.jvc.jsonplaceholderposts.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jvc.jsonplaceholderposts.BaseApplication;
import com.jvc.jsonplaceholderposts.R;
import com.jvc.jsonplaceholderposts.data.model.Comment;
import com.jvc.jsonplaceholderposts.ui.BaseView;
import com.jvc.jsonplaceholderposts.ui.Presenters.PostDetailsPresenter;
import com.jvc.jsonplaceholderposts.ui.adapters.CommentListAdapter;
import com.jvc.jsonplaceholderposts.ui.decorators.Decoration;
import com.jvc.jsonplaceholderposts.ui.interfaces.PostDetailsViewInterface;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jonathan Vargas on 23/02/2019.
 * View that manage the post details screen.
 */
public class PostDetailsActivity extends AppCompatActivity implements PostDetailsViewInterface {

    private int postId;
    private TextView postDescription;
    private TextView userName;
    private TextView userUsername;
    private TextView userPhone;
    private TextView userEmail;
    private TextView userWebsite;
    private RecyclerView commentListRecycler;

    @Inject
    PostDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        ((BaseApplication) getApplication()).getApplicationComponent().inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        postId = getIntent().getIntExtra("postSelected", 1);

        postDescription = findViewById(R.id.textview_post_details_description);
        userName = findViewById(R.id.textview_post_details_user_name);
        userUsername = findViewById(R.id.textview_post_details_user_username);
        userPhone = findViewById(R.id.textview_post_details_user_phone);
        userEmail = findViewById(R.id.textview_post_details_user_email);
        userWebsite = findViewById(R.id.textview_post_details_user_website);
        commentListRecycler = findViewById(R.id.recyclerview_post_details_comment_list);
        commentListRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        commentListRecycler.setItemAnimator(new DefaultItemAnimator());
        commentListRecycler.addItemDecoration(new Decoration(getApplicationContext(), Decoration.VERTICAL_LIST));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.setPostId(postId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_favorite) {
            presenter.updatePostAsFavorite();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPostDescription(String postDescription) {
        this.postDescription.setText(postDescription);
    }

    @Override
    public void setUserName(String userName) {
        this.userName.setText(String.format(getString(R.string.post_details_user_name), userName));
    }

    @Override
    public void setUserUsername(String userUsername) {
        this.userUsername.setText(String.format(getString(R.string.post_details_user_username), userUsername));
    }

    @Override
    public void setUserPhone(String userPhone) {
        this.userPhone.setText(String.format(getString(R.string.post_details_user_phone), userPhone));
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail.setText(String.format(getString(R.string.post_details_user_email), userEmail));
    }

    @Override
    public void setUserWebsite(String userWebsite) {
        this.userWebsite.setText(String.format(getString(R.string.post_details_user_website), userWebsite));
    }

    @Override
    public void setCommentList(List<Comment> comments) {
        commentListRecycler.setAdapter(new CommentListAdapter(comments));
    }
}
