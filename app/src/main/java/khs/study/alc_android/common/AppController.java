package khs.study.alc_android.common;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tsengvn.typekit.Typekit;

/**
 * Created by wj on 2017-03-30.
 */

public class AppController extends Application{
    private final String TAG = "JYP/"+getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        addCustomFont();
    }

    private void addCustomFont() {
        Typekit.getInstance()
//                .addNormal(Typekit.createFromAsset(this,"fonts/orange juice 2.0.ttf"))
                .addCustom1(Typekit.createFromAsset(this,"fonts/orange juice 2.0.ttf"))
                .addCustom2(Typekit.createFromAsset(this,"fonts/NotoSansCJKkr-Regular.otf"))
                .addCustom3(Typekit.createFromAsset(this,"fonts/tvN_Medium.ttf"))
                .addCustom4(Typekit.createFromAsset(this,"fonts/08SeoulNamsanEB.ttf"));
    }


}
