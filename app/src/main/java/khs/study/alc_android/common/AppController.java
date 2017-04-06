package khs.study.alc_android.common;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tsengvn.typekit.Typekit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wj on 2017-03-30.
 */

public class AppController extends Application {
    private static final String TAG = "JYP/"+"AppController";
    private static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;

    private static List<LoginListener> mLoginListeners;

    public static void addLoginListener(@NonNull LoginListener loginListener){
        mLoginListeners.add(loginListener);
    }

    public static void removeLoginListener(@NonNull LoginListener loginListener){
        mLoginListeners.remove(loginListener);
    }

    private void notifyLoginListeners(boolean loginStatus){
        if (mLoginListeners.size() > 0) {
            if(loginStatus) {
                for (LoginListener loginListener :
                        mLoginListeners) {
                    loginListener.signedIn();
                }
            }
            else {
                for (LoginListener loginListener :
                        mLoginListeners) {
                    loginListener.signedOut();
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        addCustomFont();

        initFirebaseAuth();
        mLoginListeners = new ArrayList<>();
    }

    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    notifyLoginListeners(true);
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    notifyLoginListeners(false);
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        Log.d(TAG, "initFirebaseAuth: FirebaseAuthAddAuthStateListener");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: FirebaseAuthRemoveAuthStateListener");
        if (mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    private void addCustomFont() {
        Typekit.getInstance()
//                .addNormal(Typekit.createFromAsset(this,"fonts/orange juice 2.0.ttf"))
                .addCustom1(Typekit.createFromAsset(this,"fonts/orange juice 2.0.ttf"))
                .addCustom2(Typekit.createFromAsset(this,"fonts/NotoSansCJKkr-Regular.otf"))
                .addCustom3(Typekit.createFromAsset(this,"fonts/tvN_Medium.ttf"))
                .addCustom4(Typekit.createFromAsset(this,"fonts/08SeoulNamsanEB.ttf"));
    }

    public static boolean userSignedin() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "userSignedin: "+user.getUid());
            return true;
        } else {
            // User is signed out
            Log.d(TAG, "userSignedin: signedout");
            return false;
        }
    }

    public static String getUserEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "getUserEmail: "+user);
            return user.getEmail();
        } else {
            Log.d(TAG, "userInfo: null");
            return null;
        }
    }
}
