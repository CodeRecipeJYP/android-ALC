package khs.study.alc_android.post.view;

import java.util.List;

import khs.study.alc_android.common.view.BaseView;
import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.presenter.PostPresenter;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface PostView extends BaseView<PostPresenter> {
    void showPosts(List<Post> posts);

    interface listener {
        void onShowButtonClick();
    }
}
