package khs.study.alc_android.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import khs.study.alc_android.R;

/**
 * Created by jaeyoung on 2017. 3. 29..
 */

public class ChatActivity extends Activity {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    private Button sendMsgBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendMsgBtn = (Button) findViewById(R.id.sendMsgBtn);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
