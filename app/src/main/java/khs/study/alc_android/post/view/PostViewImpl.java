package khs.study.alc_android.post.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    Button btn1, btn2,btn3,btn4,btn5, btnTest;

    @Override
    public void setMotherView(View motherView) {
        mMotherView = motherView;
        Log.d(TAG, "setMotherView: initView");
        initView();
    }

    void initView(){
        Log.d(TAG, "initView: mRecyclerView");
        mRecyclerView = (RecyclerView) mMotherView.findViewById(R.id.recycler_view);
        btn1 = (Button) mMotherView.findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onShowPostsButtonClick();
            }
        });
        btn2 = (Button) mMotherView.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onShowPostButtonClick();
            }
        });
        btn3 = (Button) mMotherView.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onNewPostButtonClick();
            }
        });
        btn4 = (Button) mMotherView.findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onModifyPostButtonClick();
            }
        });
        btn5 = (Button) mMotherView.findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onDeletePostButtonClick();
            }
        });

        btnTest = (Button) mMotherView.findViewById(R.id.buttonTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onTestButtonClick();
            }
        });

        // mPresenter.onTestViewLogic();
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
