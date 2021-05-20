package com.example.candymix;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.candymix.adapter.ProductAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    //AutoCompleteTextView autoCompleteTextView;

    String[] s1;
    int[] images ={R.drawable.slikkepind, R.drawable.lakrids, R.drawable.boller, R.drawable.vingummi, R.drawable.bolscher, R.drawable.chlade};

//    public EditText EmailAddress, Password;
//    Button signInButton, signUpButton;
//    TextView signInText, alreadyReg;
//    FirebaseAuth mFirebaseAuth;
    Icon logOut;
    private static final String TAG = "MainActivity";



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        s1 = getResources().getStringArray(R.array.slikType);

       ProductAdapter productAdapter = new ProductAdapter (this,s1, images);

       recyclerView.setAdapter(productAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        //String []option = {"50g/5kr","100g/10kr" ,"150g/15k"};
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown, option);
        //autoCompleteTextView.setAdapter(arrayAdapter);
        //autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);

//        mFirebaseAuth = FirebaseAuth.getInstance();
//        EmailAddress = findViewById(R.id.EmailAddress);
//        Password = findViewById(R.id.Password);
//        signInButton = findViewById(R.id.signInButton);
//        signInText = findViewById(R.id.signInText);
//        alreadyReg = findViewById(R.id.alreadyReg);
//        signUpButton = findViewById(R.id.signUpButton);
//
//        signUpButton.setOnClickListener(v -> {
//           String email = EmailAddress.getText().toString();
//             String password = Password.getText().toString();
//             if(email.isEmpty()){
//                 EmailAddress.setError("Please Enter Email");
//                 EmailAddress.requestFocus();
//             }
//             else if(password.isEmpty()){
//                 Password.setError("Please Enter Password");
//                 Password.requestFocus();
//             }
//             else if (email.isEmpty() && password.isEmpty()){
//                 Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
//             }
//             else if (!(email.isEmpty() && password.isEmpty())){
//                 mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                     @Override
//                     public void onComplete(@NonNull Task<AuthResult> task) {
//                         if(!task.isSuccessful()){
//                             Toast.makeText(MainActivity.this, "Sign Up Unsuccessful, Try Again!", Toast.LENGTH_SHORT).show();
//                         }
//                         else{}
//                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
//
//                     }
//                });
//
//             }
//             else{
//                 Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
//             }
//         });
//        alreadyReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(i);
//            }
//        });
//If their is no user logged it will show the login register activity
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_first, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.logOut:
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                AuthUI.getInstance().signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    LogAct();

                                } else {
                                    Log.e(TAG, "onComplete", task.getException());
                                }

                            }
                        });
            case R.id.pindslik:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.profilli:
                Intent intentt = new Intent(this, Profilos.class);
                startActivity(intentt);
                finish();


                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LogAct() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}

