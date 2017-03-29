package khs.study.alc_android.chat.model;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import khs.study.alc_android.chat.domain.Message;
import khs.study.alc_android.chat.presenter.ChatPresenter;
import khs.study.alc_android.utils.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 3. 29..
 */

public class ChatServiceImpl implements ChatService {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    ChatPresenter mPresenter;

    MessageDao mMessageDao;
    RestClient<MessageDao> mRestClient;

    List<Message> mMessages;

    public ChatServiceImpl() {
        mRestClient = new RestClient<>();
        mMessageDao = mRestClient.getClient(MessageDao.class);
    }

    @Override
    public void setPresenter(ChatPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getMessages(final GetMessagesListener getMessagesListener) {
        Log.d(TAG, "getMessages: ");

        Call<List<Message>> call = mMessageDao.getMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    mMessages = response.body();
                    getMessagesListener.onGetMessagesSuccess(mMessages);
                    Log.d(TAG, "onResponse: Response is Successful");
                } else {
                    Log.d(TAG, "onResponse: Unexpected response");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+call.toString());
            }
        });
    }

    @Override
    public void postMessage(String user, String chat, String title, final ChatService.PostMessageListener postMessageListener) {
        Message message = new Message(null, DateFormat.getDateTimeInstance().format(new Date()), title, user, chat);

        Call<Message> call = mMessageDao.postMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response is Successful");
                    postMessageListener.onPostMessageSuccess(response.body());
                }
                else {
                    Log.d(TAG, "onResponse: Unexpected response");
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d(TAG, "onFailure: "+call.toString());
            }
        });
    }
}