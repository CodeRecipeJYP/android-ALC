package khs.study.alc_android.post.presenter;

import khs.study.alc_android.common.presenter.BasePresenter;
import khs.study.alc_android.post.model.PostService;
import khs.study.alc_android.post.view.PostView;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface PostPresenter extends BasePresenter<PostView,PostService> {
    void getPosts();

    void showPosts();
}
