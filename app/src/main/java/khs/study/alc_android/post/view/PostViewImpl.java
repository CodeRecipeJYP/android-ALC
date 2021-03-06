package khs.study.alc_android.post.view;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import khs.study.alc_android.R;
import khs.study.alc_android.post.PostActivity;
import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.presenter.PostPresenter;
import khs.study.alc_android.post.view.recyclerview.adapter.PostAdapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by jaeyoung on 2017. 3. 27..
 */

    public class PostViewImpl implements PostView {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    View mMotherView;
    PostPresenter mPresenter;

    RecyclerView mRecyclerView;
    PostAdapter mPostAdapter;

    private String dialogPostTitleText,dialogPostContentText;

    @Override
    public void setMotherView(View motherView) {
        mMotherView = motherView;
        Log.d(TAG, "setMotherView: initView");
        initView();
    }

    void initView(){
        Log.d(TAG, "initView: mRecyclerView");
        mRecyclerView = (RecyclerView) mMotherView.findViewById(R.id.recycler_view);
        final SwipeRefreshLayout mSwipeRefresh = (SwipeRefreshLayout) mMotherView.findViewById(R.id.swiperefresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onShowPostsButtonClick();
                mSwipeRefresh.setRefreshing(false);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) mMotherView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onNewPostButtonClick();
            }
        });
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

    public void showNewPostDialog(){
        LayoutInflater inflater = (LayoutInflater)mMotherView.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.custom_post_layout,null);
        final EditText title = (EditText)view.findViewById(R.id.dialogPostTitleText);
        final EditText content = (EditText)view.findViewById(R.id.dialogPostContentText);
        new AlertDialog.Builder(mMotherView.getContext())
                .setView(view)
                .setPositiveButton("포스트", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: ");
                        String titleText = title.getText().toString();
                        String contentText = content.getText().toString();
                        Toast.makeText(mMotherView.getContext(), "titleText:"+titleText+",contentText:"+contentText, Toast.LENGTH_SHORT).show();
                        setDialogPostTitleText(title.getText().toString());
                        setDialogPostContentText(content.getText().toString());
                        mPresenter.onSendPostBtnClick();
                    }
                })

                .setNegativeButton("나가기", null)
                .show();
        }

    public void setDialogPostTitleText(String dialogPostTitleText) {
        this.dialogPostTitleText = dialogPostTitleText;
    }

    public void setDialogPostContentText(String dialogPostContentText) {
        this.dialogPostContentText = dialogPostContentText;
    }

    public String getDialogPostTitleText() {
        Log.d(TAG, "getDialogPostTitleText: "+dialogPostTitleText);
        return dialogPostTitleText;
    }

    public String getDialogPostContentText() {
        Log.d(TAG, "getDialogPostContentText: "+dialogPostTitleText);
        return dialogPostContentText;
    }
}
