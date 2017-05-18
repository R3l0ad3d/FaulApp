package com.example.r3l0ad3d.tourmate;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.ModelClass.UserInformation;
import com.example.r3l0ad3d.tourmate.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {


    private ActivitySignUpBinding binding;

    //database reference
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //private FirebaseUser firebaseUser;

    //object
    private UserInformation userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        userInfo = new UserInformation();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("UserInformation");

        auth = FirebaseAuth.getInstance();

    }

    public void signUpBtn(View view) {
        Map<String,UserInformation> map = new HashMap<>();

        String eMail = binding.etEmailSignUp.getText().toString().trim();
        String pass = binding.etPasswordSignUp.getText().toString().trim();

        if(binding.etFullNameSignUp.getText().toString().equals("")){
            binding.etFullNameSignUp.setError("Empty field found");
        }else if(binding.etEmailSignUp.getText().toString().equals("")){
            binding.etEmailSignUp.setError("Empty field found");
        }else if(binding.etPasswordSignUp.equals("")){
            binding.etPasswordSignUp.setError("Empty field found");
        }else if(binding.etConfirmPasswordSignUp.getText().toString().equals("")){
            binding.etConfirmPasswordSignUp.setError("Empty field found");
        }else if(!(binding.etPasswordSignUp.getText().toString().equals(binding.etConfirmPasswordSignUp.getText().toString()))){
            binding.etConfirmPasswordSignUp.setError("Password not match");
        }else{
            userInfo.setFullName(binding.etFullNameSignUp.getText().toString());
            userInfo.seteMail(binding.etEmailSignUp.getText().toString());
            userInfo.setPassWord(binding.etPasswordSignUp.getText().toString());

            auth.createUserWithEmailAndPassword(eMail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        String uId = user.getUid();
                        userInfo.setUserID(uId);
                        databaseReference.child(uId).setValue(userInfo);
                        Toast.makeText(getApplicationContext(),"Sign Up success",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }
}
