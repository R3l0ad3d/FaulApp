package com.example.r3l0ad3d.tourmate;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;

public class LogInActivity extends AppCompatActivity {

    private ActivityLogInBinding binding;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();

    }

    public void onClickSignUpActivity(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void homeActivity(View view) {
        String eMail = binding.etUserNameSignIn.getText().toString().trim();
        String pass = binding.etPasswordSignIn.getText().toString().trim();
        if(eMail.equals("")){
            binding.etUserNameSignIn.setError("empty field found");
        }else if ((pass.equals(""))){
            binding.etPasswordSignIn.setError("empty field found");
        }else {
            auth.signInWithEmailAndPassword(eMail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String uId= task.getResult().getUser().getUid();
                        Toast.makeText(getApplicationContext(),"Log In Success",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("userId",uId);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }
}
