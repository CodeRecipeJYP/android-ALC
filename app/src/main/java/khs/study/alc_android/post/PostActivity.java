package khs.study.alc_android.post;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.List;

import khs.study.alc_android.R;
import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.model.PostService;
import khs.study.alc_android.post.model.PostServiceImpl;
import khs.study.alc_android.post.presenter.PostPresenter;
import khs.study.alc_android.post.view.PostView;
import khs.study.alc_android.post.view.PostViewImpl;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public class PostActivity extends Activity implements PostPresenter, PostService.listener {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    PostService mService;
    PostView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        setService(new PostServiceImpl());
        mService.setPresenter(this);
        mService.setListener(this);

        attachView(new PostViewImpl());
        mView.setPresenter(this);

        mView.setMotherView(this.getWindow().getDecorView());

        mService.getPosts();
    }

    @Override
    public void attachView(PostView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void setService(PostService service) {
        mService = service;
    }

    @Override
    public void getPosts() {

    }

    @Override
    public void showPosts(List<Post> posts) {
        Log.d(TAG, "showPosts: ");
        mView.showPosts(posts);
    }

    // ----------------------------- listener -----------------------------


    @Override
    public void onGetPostsSuccess(List<Post> posts) {
        Log.d(TAG, "onGetPostsSuccess: "+posts.toString());
        showPosts(posts);
    }
}
