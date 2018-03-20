package com.myapp.railwayapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myapp.railwayapp.R;



public class LoginActivity extends BaseActivity {

    private View progressBar;
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.login_activity_username);
        password = (EditText) findViewById(R.id.login_activity_password);
        usernameWrapper=findViewById(R.id.login_activity_username_wrapper);
        passwordWrapper=findViewById(R.id.login_activity_password_wrapper);
        usernameWrapper.setHint("ENTER USERNAME");
        passwordWrapper.setHint("ENTER PASSWORD");
        passwordWrapper.setPasswordVisibilityToggleEnabled(true);
        progressBar=findViewById(R.id.login_activity_progressbar);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(getApplicationContext(), "Logged In As " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    application.getAuth().getUser().setLoggedIn(true);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "User Not Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    public void login(View v) {
        if(userName.getText().toString().length()==0||password.getText().toString().length()==0)
        {
            Toast.makeText(this,"Username or Password is Empty",Toast.LENGTH_SHORT).show();

        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(userName.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            } else {
                                application.getAuth().getUser().setLoggedIn(true);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                Toast.makeText(LoginActivity.this, "Login successfull.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
        }
    }

    public void sign(View v)
    {
        startActivity(new Intent(LoginActivity.this,registration.class));
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

}

