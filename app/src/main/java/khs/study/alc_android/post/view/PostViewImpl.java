package khs.study.alc_android.post.view;

import android.util.Log;
import android.view.View;

import java.util.List;

import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.presenter.PostPresenter;

/**
 * Created by jaeyoung on 2017. 3. 27..
 */

public class PostViewImpl implements PostView {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    View mMotherView;
    PostPresenter mPresenter;


    @Override
    public void setMotherView(View motherView) {
        mMotherView = motherView;

    }

    @Override
    public void setPresenter(PostPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPosts(List<Post> posts) {
        Log.d(TAG, "showPosts: "+posts);
    }
}
