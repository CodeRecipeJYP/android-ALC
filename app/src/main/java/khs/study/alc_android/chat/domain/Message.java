package khs.study.alc_android.chat.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jaeyoung on 2017. 3. 30..
 */

public class Message implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("created_date")
    String created_date;
    @SerializedName("user")
    String user;
    @SerializedName("content")
    String content;
    @SerializedName("chat")
    String chat;

    public Message() {
    }

    public Message(String user, String content) {
        this.user = user;
        this.content = content;
    }

    public Message(String id, String created_date, String user, String content, String chat) {
        this.id = id;
        this.created_date = created_date;
        this.user = user;
        this.content = content;
        this.chat = chat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
