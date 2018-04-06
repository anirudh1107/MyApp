package com.myapp.railwayapp.Activities;

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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.myapp.railwayapp.R;

public class PasswordChange extends BaseActivity {
    private TextView current_password,new_password;
    private Button button;
    private ProgressBar progressBar;
    String pwd1,pwd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);



        current_password=findViewById(R.id.current_password);
        new_password=findViewById(R.id.newpassword);
        progressBar=findViewById(R.id.change_progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        button=findViewById(R.id.password_change_button);

        final FirebaseUser user=mAuth.getInstance().getCurrentUser();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String email=user.getEmail();

                pwd1=current_password.getText().toString();
                pwd2=new_password.getText().toString();

                AuthCredential credential= EmailAuthProvider.getCredential(email,pwd1);

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            user.updatePassword(pwd2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(PasswordChange.this,"Password Updated",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                    else
                                    {
                                        Toast.makeText(PasswordChange.this,"Password Not Updated",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(PasswordChange.this,"Auth Problem",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}
