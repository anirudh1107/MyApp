package com.myapp.railwayapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.myapp.railwayapp.R;

public class ForgotPassword extends BaseActivity {
    private TextView email;
    private Button forget;
    private ProgressBar p;
    private String user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        email=findViewById(R.id.emailid);
        forget=findViewById(R.id.password_forgot_button);
        p=findViewById(R.id.forgot_progressbar);
        p.setVisibility(View.INVISIBLE);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setVisibility(View.VISIBLE);
                user_email=email.getText().toString();
                mAuth.getInstance().sendPasswordResetEmail(user_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    p.setVisibility(View.INVISIBLE);
                                    Toast.makeText(ForgotPassword.this,"Link sent",Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
                                    finish();
                                }
                                else
                                {
                                    p.setVisibility(View.INVISIBLE);
                                    Toast.makeText(ForgotPassword.this,"Invalid Email",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}
