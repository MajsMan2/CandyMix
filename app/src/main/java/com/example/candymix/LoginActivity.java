package com.example.candymix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText EmailAddress, Password;
    Button signInButton, signUpButton;
    TextView signInText, alreadyReg;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private static final String TAG = "LoginRegisterActivity";
    int AUTHUI_REQUEST_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }

    public void handleLogInRegister(View view) {

        List<AuthUI.IdpConfig> provider = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .setLogo(R.drawable.ic_pindslik)
                .setAlwaysShowSignInMethodScreen(true)
                .build();

        startActivityForResult(intent, AUTHUI_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHUI_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //The user is signed in or a new user is made
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "onActivityResult:" + user.getEmail());
                if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                    Toast.makeText(this, "Welcome New User", Toast.LENGTH_SHORT).show();
                } else {
                    //This is a returning user
                    Toast.makeText(this, "Welcome Back Again", Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

            }
        }
    }
// Endnu mere forsøg
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        EmailAddress = findViewById(R.id.EmailAddress);
//        Password = findViewById(R.id.Password);
//        signInButton = findViewById(R.id.signInButton);
//        signInText = findViewById(R.id.signInText);
//        alreadyReg = findViewById(R.id.alreadyReg);
//        signUpButton = findViewById(R.id.signUpButton);
//
//        mAuthStateListener = firebaseAuth -> {
//            FirebaseUser mFireBaseUser = mFirebaseAuth.getCurrentUser();
//            if(mFireBaseUser != null){
//                Toast.makeText(LoginActivity.this,"You are logged in", Toast.LENGTH_SHORT ).show();
//                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(i);
//            }
//            else{
//                Toast.makeText(LoginActivity.this,"Please Login", Toast.LENGTH_SHORT ).show();
//
//            }
//        };
//        signInButton.setOnClickListener(v -> {
//            String email = EmailAddress.getText().toString();
//            String password = Password.getText().toString();
//            if(email.isEmpty()){
//                EmailAddress.setError("Please Enter Email");
//                EmailAddress.requestFocus();
//            }
//            else if(password.isEmpty()){
//                Password.setError("Please Enter Password");
//                Password.requestFocus();
//            }
//            else if(email.isEmpty() && password.isEmpty()){
//                Toast.makeText(LoginActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
//            }
//            else if(!(email.isEmpty() && password.isEmpty())){
//                mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(!task.isSuccessful()){
//                            Toast.makeText(LoginActivity.this,"Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
//                    }
//                        else {
//                            Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
//                                startActivity(intToHome);
//                }
//            }
//                });
//            }
//            else{
//                Toast.makeText(LoginActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intSignUp = new Intent (LoginActivity.this, MainActivity.class);
//                startActivity(intSignUp);
//            }
//        });
//            }
//            @Override
//            protected void onStart(){
//        super.onStart();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//            }
        }