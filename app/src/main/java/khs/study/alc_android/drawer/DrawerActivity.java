package khs.study.alc_android.drawer;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.EditText;
import android.widget.Toast;

import khs.study.alc_android.R;
import khs.study.alc_android.chat.ChatActivity;
import khs.study.alc_android.common.AppController;
import khs.study.alc_android.common.LoginListener;
import khs.study.alc_android.consts.Config;
import khs.study.alc_android.login.LoginActivity;
import khs.study.alc_android.post.PostActivity;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    private NavigationView mNavigationView;
    private LoginListener mLoginListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG, "onCreate:");
        initLoginListener();
        Log.d(TAG, "onCreate: addLoginListener");
        AppController.addLoginListener(mLoginListener);
    }

    private void initLoginListener() {
        mLoginListener = new LoginListener() {
            @Override
            public void signedOut() {
                showLoginStatus(false);
            }

            @Override
            public void signedIn() {
                showLoginStatus(true);
            }
        };
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: removeLoginListener");
        AppController.removeLoginListener(mLoginListener);
        super.onDestroy();
    }

    protected void initDrawerView(){
        Log.d(TAG, "initDrawerView: ");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        showLoginStatus(AppController.userSignedin());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            Log.d(TAG, "onBackPressed: Drawer is opened");
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.d(TAG, "onBackPressed: Drawer is not opened");
            super.onBackPressed();
        }
    }

    public void showLoginStatus(boolean loginStatus) {
        Log.d(TAG, "showLoginStatus: ");
        String loginStatusMsg, userName, userEmail;
        if (loginStatus) {
            Log.d(TAG, "onPrepareOptionsMenu: if Signed in");
            loginStatusMsg = "로그아웃";
            userName = AppController.getUserName();
            userEmail = AppController.getUserEmail();
        }
        else {
            Log.d(TAG, "onPrepareOptionsMenu: if not Signed in");
            loginStatusMsg = "로그인";
            userName = Config.defaultUserName;
            userEmail = Config.defaultUserEmail;
        }
        Log.d(TAG, "showLoginStatus: setTitle to LoginStatus");
        mNavigationView.getMenu().findItem(R.id.nav_login).setTitle(loginStatusMsg);
        View headerView = mNavigationView.getHeaderView(0);
        ((TextView) (headerView.findViewById(R.id.name))).setText(userName);
        ((TextView) (headerView.findViewById(R.id.email))).setText(userEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_board) {
            startActivity(new Intent(getApplicationContext(), PostActivity.class));
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(getApplicationContext(), ChatActivity.class));
        } else if (id == R.id.nav_album) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_login) {
            if (AppController.userSignedin()) {
                // if user is signed -> sign out
                FirebaseAuth.getInstance().signOut();
            }
            else
            {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
//            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//            final View view = inflater.inflate(R.layout.custom_login_layout,null);
//             final EditText ID = (EditText)view.findViewById(R.id.editTextLoginID);
//             final EditText PW = (EditText)view.findViewById(R.id.editTextLoginPW);
//             new AlertDialog.Builder(view.getContext())
//                     .setView(view)
//                     .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
//                         @Override
//                         public void onClick(DialogInterface dialog, int which) {
//                         }
//                     })

//                     .setNegativeButton("나가기", null)
//                     .show();
        } else if (id == R.id.nav_signin) {
            
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

