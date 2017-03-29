package khs.study.alc_android.chat.model;

import java.util.List;

import khs.study.alc_android.chat.domain.Message;
import khs.study.alc_android.chat.presenter.ChatPresenter;
import khs.study.alc_android.common.model.BaseService;

/**
 * Created by jaeyoung on 2017. 3. 29..
 */

public interface ChatService extends BaseService<ChatPresenter> {
    void getMessages(final GetMessagesListener getMessagesListener);
    interface GetMessagesListener{
        void onGetMessagesSuccess(List<Message> messages);
    }

    void postMessage(String user, String chat, String title, final PostMessageListener postMessageListener);
    interface PostMessageListener{
        void onPostMessageSuccess(Message message);
    }
}
