package khs.study.alc_android.post.model;

import java.util.List;

import khs.study.alc_android.post.domain.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface PostDao {
    @GET("api/post/{post_id}")
    Call<Post> getPost(@Path("post_id") String id);

    @GET("api/post")
    Call<List<Post>> getPosts();

    @POST("api/post")
    Call<Post> postPost(@Body Post post);

    @PUT("api/post/{post_id}")
    Call<Post> putPost(@Path("psot_id") String id, @Body Post post);

    @DELETE("api/post/{post_id}")
    Call<Void> deletePost(@Path("post_id") String id);
}
