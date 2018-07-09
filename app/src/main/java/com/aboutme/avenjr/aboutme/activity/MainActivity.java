package com.aboutme.avenjr.aboutme.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.services.NetworkService;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.home_image)
    LinearLayout home_image;

    @BindView(R.id.layout_continue_with_facebook)
    Button continueWithFacebook;

    @BindView(R.id.continue_with_google)
    Button getContinueWithGoogle;

    @BindView(R.id.sign_up_with_mail)
    Button continueWithMail;

    @BindView(R.id.sign_in)
    LinearLayout signIn;

    @BindView(R.id.main_layout)
    RelativeLayout body;

    public static GoogleSignInClient mGoogleApiClient;
    public static FirebaseAuth mAuth;
    Activity activity;
    Context context;
    SharedPreferencesUtil preference;
    Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.context = getApplicationContext();
        this.activity = this;
        this.preference = new SharedPreferencesUtil(getApplicationContext());

        verifyAlreadyLogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        registerForContextMenu(home_image);
        setupProgress(body);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = GoogleSignIn.getClient(this, gso);

        this.service = new Intent(context, NetworkService.class);
        context.startService(service);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            onBackPressed();
        }

        continueWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonAnimation(continueWithFacebook);
                DialogUtil.yesDialog(activity, "Sorry", "No functionality implemented", click -> {
                    removeAnimation(continueWithFacebook);
                });
            }
        });

        continueWithMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonAnimation(continueWithMail);
                Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
                removeAnimation(continueWithMail);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonAnimation(signIn);
                Intent intent = new Intent(getBaseContext(), SignInActivity.class);
                intent.putExtra("login_with", "signIn");
                startActivity(intent);
                removeAnimation(signIn);
            }
        });
    }

    private void verifyAlreadyLogin() {
        if(!preference.getLoginWith().equals("noLogin")){
            Intent intent = new Intent(activity,MpinActivity.class);
            activity.finish();
            startActivity(intent);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleApiClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                removeAnimation(getContinueWithGoogle);
                hideProgress();
                Toast.makeText(getBaseContext(), "Sign-in failed.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.continue_with_google)
    public void googleSignIn() {
        startButtonAnimation(getContinueWithGoogle);
        //                https://developers.google.com/identity/sign-in/android/
        showProgress();
        signIn();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_environment_menu, menu);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Firebase", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            hideProgress();
                            DialogUtil.yesDialog(activity, "Success", "Sign in with email id "
                                    + user.getEmail() + " Success.", click -> {
                                Intent intent = new Intent(getApplicationContext(), MpinActivity.class);
                                preference.setName(user.getDisplayName());
                                preference.setEmail(user.getEmail());
                                preference.setProfileImageUrl(user.getPhotoUrl().toString());
                                preference.putLoginWith("google");
                                startActivity(intent);
                                activity.finish();
                                removeAnimation(continueWithFacebook);
                            });
                        } else {
                            hideProgress();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
            verifyNetwork();
            super.onResume();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.prod:
                DialogUtil.yesDialog(activity, "Production", "you are on production!..", click -> {

                });
                break;
            case R.id.prepod:
                DialogUtil.yesDialog(activity, "Prepod", "you are on Propod!..", click -> {

                });
                break;
            case R.id.dev:
                DialogUtil.yesDialog(activity, "Integration", "you are on integration!..", click -> {

                });
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void startButtonAnimation(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", 50f);
        animation.setDuration(500);
        animation.start();
    }

    public void removeAnimation(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", 0f);
        animation.setDuration(500);
        animation.start();
    }

    @Override
    public void onBackPressed() {
        hideProgress();
        activity.finish();
    }

    @Override
    protected void onDestroy() {
        stopService(service);
        super.onDestroy();
    }
}
