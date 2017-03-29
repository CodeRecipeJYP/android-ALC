package khs.study.alc_android.chat.model;

import java.util.List;

import khs.study.alc_android.chat.domain.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 2017. 3. 29..
 */

public interface MessageDao {
    @GET("api/message/{message_id}/")
    Call<Message> getMessage(@Path("message_id") String id);

    @GET("api/message/")
    Call<List<Message>> getMessages();

    @POST("api/message/")
    Call<Message> postMessage(@Body Message message);

    @PUT("api/message/{message_id}/")
    Call<Message> putMessage(@Path("message_id") String id, @Body Message message);

    @DELETE("api/message/{message_id}/")
    Call<Void> deleteMessage(@Path("message_id") String id);
}
