package com.example.candymix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Profilos extends AppCompatActivity {

    private static final String TAG = "Profilos";

    ImageView pindslikki;
    TextInputEditText displayNameEdit;
    Button updateProfileButton;
    ProgressBar progressBar;

    String DISPLAY_NAME = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilos);

        pindslikki = findViewById(R.id.pindslikki);
        displayNameEdit = findViewById(R.id.displayNameEdit);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        progressBar = findViewById(R.id.progressBar);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        if (user != null) {
            Log.d(TAG, "onCreate: " + user.getDisplayName());
            if (user.getDisplayName() != null) {
                displayNameEdit.setText(user.getDisplayName());
                displayNameEdit.setSelection(user.getDisplayName().length());
            }
        }
        progressBar.setVisibility(View.GONE);


    }

    public void updateProfile(View view) {

        view.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        DISPLAY_NAME = displayNameEdit.getText().toString();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = firebaseUser.getEmail();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(email)
                .setDisplayName(DISPLAY_NAME)
                .build();

        firebaseUser.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        view.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Profilos.this, "Successfully Updated Profile", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onFailure: ", e.getCause());

                    }
                });
    }

}