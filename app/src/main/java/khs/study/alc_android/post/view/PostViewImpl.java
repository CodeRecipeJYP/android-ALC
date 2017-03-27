package khs.study.alc_android.post.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import khs.study.alc_android.R;
import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.presenter.PostPresenter;
import khs.study.alc_android.post.view.recyclerview.adapter.PostAdapter;

/**
 * Created by jaeyoung on 2017. 3. 27..
 */

public class PostViewImpl implements PostView {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    View mMotherView;
    PostPresenter mPresenter;

    RecyclerView mRecyclerView;
    PostAdapter mPostAdapter;

    @Override
    public void setMotherView(View motherView) {
        mMotherView = motherView;
        Log.d(TAG, "setMotherView: initView");
        initView();
    }

    void initView(){
        Log.d(TAG, "initView: mRecyclerView");
        mRecyclerView = (RecyclerView) mMotherView.findViewById(R.id.recycler_view);
    }

    @Override
    public void setPresenter(PostPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPosts(List<Post> posts) {
        Log.d(TAG, "showPosts: "+posts);
        mPostAdapter = new PostAdapter(posts);
        mRecyclerView.setAdapter(mPostAdapter);
    }
}
