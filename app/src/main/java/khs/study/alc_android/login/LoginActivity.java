package khs.study.alc_android.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import khs.study.alc_android.R;
import khs.study.alc_android.post.PostActivity;

/**
 * Created by jaeyoung on 2017. 3. 29..
 */

public class LoginActivity extends Activity {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    private Button goPostActivityBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goPostActivityBtn = (Button) findViewById(R.id.goPostActivity);
        goPostActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PostActivity.class));
            }
        });
    }
}
