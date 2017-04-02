package khs.study.alc_android.post;

import android.app.Application;
import com.tsengvn.typekit.Typekit;

/**
 * Created by wj on 2017-03-30.
 */

public class FontCustom extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"fonts/orange juice 2.0.ttf"))
                .addCustom1(Typekit.createFromAsset(this,"fonts/orange juice 2.0.ttf"))
                .addCustom2(Typekit.createFromAsset(this,"fonts/NotoSansCJKkr-Regular.otf"))
                .addCustom3(Typekit.createFromAsset(this,"fonts/tvN_Medium.ttf"))
                .addCustom4(Typekit.createFromAsset(this,"fonts/08SeoulNamsanEB.ttf"));
    }
}
