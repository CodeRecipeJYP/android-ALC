package khs.study.alc_android.post.model;

import java.util.List;

import khs.study.alc_android.common.model.BaseService;
import khs.study.alc_android.post.domain.Post;
import khs.study.alc_android.post.presenter.PostPresenter;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface PostService extends BaseService<PostPresenter> {
    void getPosts(final GetPostsListener getPostsListener);
    interface GetPostsListener{
        void onGetPostsSuccess(List<Post> posts);
    }
}
