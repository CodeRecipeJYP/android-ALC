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
    @SerializedName("title")
    String title;
    @SerializedName("user")
    String user;
    @SerializedName("chat")
    String chat;

    public Message(String id, String created_date, String title, String user, String chat) {
        this.id = id;
        this.created_date = created_date;
        this.title = title;
        this.user = user;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
