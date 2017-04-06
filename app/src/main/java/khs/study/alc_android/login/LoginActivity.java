package khs.study.alc_android.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.Arrays;

import khs.study.alc_android.R;
import khs.study.alc_android.drawer.DrawerActivity;
import khs.study.alc_android.post.PostActivity;

/**
 * Created by jaeyoung on 2017. 3. 29..
 */

public class LoginActivity extends DrawerActivity {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    private Button goPostActivityBtn;
    private LoginButton btnRegisterFacebook;
    private CallbackManager callbackManager;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        }
    };

    private void initFacebookLogin() {
        //FacebookSdk.sdkInitialize(getApplicationContext());
        btnRegisterFacebook = (LoginButton)findViewById(R.id.btnRegisterFacebook);

        callbackManager = CallbackManager.Factory.create();
        btnRegisterFacebook.setReadPermissions(Arrays.asList("public_profile, email"));
        btnRegisterFacebook.registerCallback(callbackManager, callback);
        btnRegisterFacebook.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Log.d(TAG, "onComplete: Authentication failed.");
                        }
                    }
                });
    }

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel: 로그인을 취소 하였습니다!");
        }
        @Override
        public void onError(FacebookException e) {
            Log.d(TAG, "onError: 소셜 로그인 오류입니다.");
        }
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            String socialId = null, name = null;
                            try {
                                socialId = object.getString("id");
                                name = object.getString("name");
                            }catch (Exception e){}

                            Log.d(TAG, "onCompleted: "+socialId);
                            Log.d(TAG, "onCompleted: "+name);
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name");
            request.setParameters(parameters);
            request.executeAsync();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        super.initDrawerView();


        goPostActivityBtn = (Button) findViewById(R.id.goPostActivity);
        goPostActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PostActivity.class));
            }
        });

        initFacebookLogin();
    }
}
