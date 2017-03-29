package khs.study.alc_android.post;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class PostActivity extends Activity implements PostPresenter{
    private final String TAG = "JYP/"+getClass().getSimpleName();

    PostService mService;
    PostView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        setService(new PostServiceImpl());
        mService.setPresenter(this);

        attachView(new PostViewImpl());
        mView.setPresenter(this);

        mView.setMotherView(this.getWindow().getDecorView());
        onShowPostsButtonClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onShowPostsButtonClick();
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
        mService.getPosts(new PostService.GetPostsListener() {
            @Override
            public void onGetPostsSuccess(List<Post> posts) {
                showPosts(posts);
            }
        });
    }

    @Override
    public void showPosts(List<Post> posts) {
        Log.d(TAG, "showPosts: ");
        mView.showPosts(posts);
    }
    
    // Listener


    @Override
    public void onShowPostsButtonClick() {
        Log.d(TAG, "onShowPostsButtonClick: ");
        getPosts();
    }

    @Override
    public void onShowPostButtonClick() {
        Log.d(TAG, "onShowPostButtonClick: ");
    }

    @Override
    public void onNewPostButtonClick() {
        Log.d(TAG, "onNewPostButtonClick: ");
        String title = mView.getNewPostTitle();
        String content = mView.getNewPostContent();
        String author = "1";
        mService.postPost(author, title, content, new PostService.PostPostListener() {
            @Override
            public void onPostPostSuccess(Post post) {
                onShowPostsButtonClick();
            }
        });
    }

    @Override
    public void onDeletePostButtonClick() {
        Log.d(TAG, "onDeletePostButtonClick: ");
    }

    @Override
    public void onModifyPostButtonClick() {
        Log.d(TAG, "onModifyPostButtonClick: ");
    }


    // for test

    @Override
    public void onTestButtonClick() {
        Log.d(TAG, "onTestButtonClick: ");
        testServiceLogic();
    }

    @Override
    public void onTestViewLogic() {
        Toast.makeText(this, "View Logic Test", Toast.LENGTH_SHORT).show();
    }

    void testServiceLogic(){

    }
}
