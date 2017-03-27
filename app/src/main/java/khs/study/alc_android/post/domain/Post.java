package khs.study.alc_android.post.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public class Post implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("uploaded_date")
    private String uploaded_date;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    public Post(String id, String uploaded_date, String title, String content) {
        this.id = id;
        this.uploaded_date = uploaded_date;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploaded_date() {
        return uploaded_date;
    }

    public void setUploaded_date(String uploaded_date) {
        this.uploaded_date = uploaded_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
