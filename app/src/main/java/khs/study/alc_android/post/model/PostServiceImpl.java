package khs.study.alc_android.post.model;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import khs.study.alc_android.R;
import khs.study.alc_android.drawer.DrawerActivity;
import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.presenter.PostPresenter;
import khs.study.alc_android.utils.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 3. 27..
 */

public class PostServiceImpl implements PostService {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    PostPresenter mPresenter;

    PostDao mPostDao;
    RestClient<PostDao> mRestClient;

    List<Post> mPosts;

    public PostServiceImpl() {
        mRestClient = new RestClient<>();
        mPostDao = mRestClient.getClient(PostDao.class);
    }

    @Override
    public void setPresenter(PostPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getPosts(final GetPostsListener getPostsListener) {
        Log.d(TAG, "getPosts: ");

        Call<List<Post>> call = mPostDao.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    mPosts = response.body();
                    getPostsListener.onGetPostsSuccess(mPosts);
                    Log.d(TAG, "onResponse: Response is Successful");
                } else {
                    Log.d(TAG, "onResponse: Unexpected response");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+call.toString());
            }
        });
    }

    @Override
    public void postPost(String user, String title, String content, final PostPostListener postPostListener) {
        Post post = new Post(user, DateFormat.getDateTimeInstance().format(new Date()), title, content);

        Call<Post> call = mPostDao.postPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response is Successful");
                    postPostListener.onPostPostSuccess(response.body());
                }
                else {
                    Log.d(TAG, "onResponse: Unexpected response");
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: "+call.toString());
            }
        });
    }

}
