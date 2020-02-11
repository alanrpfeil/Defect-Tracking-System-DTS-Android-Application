package com.example.ejgallodts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase database;
    DatabaseReference users;
    ProgressBar progBar;
    user current_user = new user();

    EditText editPassword, editEmail;
    Button signupButton, loginButton;
    String email;

    boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progBar = findViewById(R.id.progressBar);
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        editEmail = (EditText) findViewById(R.id.userEmail);
        editPassword = (EditText) findViewById(R.id.password);
        final Switch admin_mode = (Switch) findViewById(R.id.switch1);

        signupButton = (Button) findViewById(R.id.signupbutton);
        loginButton = (Button) findViewById(R.id.loginbutton);

        signupButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            progBar.setVisibility(View.VISIBLE);

            email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please Enter Credentials:", Toast.LENGTH_SHORT);
            } else if (email.isEmpty()) {
                editEmail.setError("Please Enter Email:");
                editEmail.requestFocus();
                progBar.setVisibility(View.INVISIBLE);
            } else if (password.isEmpty()) {
                editPassword.setError("Please Enter Password:");
                editPassword.requestFocus();
                progBar.setVisibility(View.INVISIBLE);
            } else if (!email.isEmpty() && !password.isEmpty()) {

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Sign Up Unsuccessful", Toast.LENGTH_SHORT);
                        } else {
                            current_user.email = email;
                            CollectionReference UsersRef = db.collection("Users");
                            Map<String, Object> newUser = new HashMap<>();
                            newUser.put("email", current_user.email);
                            if (admin_mode.isChecked()) {
                                newUser.put("rank", true);
                                current_user.isAdmin = true;
                            } else {
                                newUser.put("rank", false);
                                current_user.isAdmin = false;
                            }
                            UsersRef.add(newUser);

                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            i.putExtra("isAdmin", current_user.isAdmin);
                            //i.putExtra(""); // putting object in there
                            progBar.setVisibility(View.INVISIBLE);
                            startActivity(i);
                        }
                    }
                });
            }
        }
    });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progBar.setVisibility(View.VISIBLE);

                email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    progBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Please Fill In All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                db.collection("Users")
                                        .whereEqualTo("email", email)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {

                                                current_user.email = (String) document.get("email");
                                                current_user.isAdmin = (boolean) document.get("rank");
                                                isAdmin = (boolean) document.get("rank");
                                                current_user.firstname = (String) document.get("firstname");
                                                current_user.user_site = (String) document.get("user_site");
                                                current_user.department = (String) document.get("user_department");
                                                current_user.user_subdepartment = (String) document.get("user_subdepartment");
                                                current_user.primarylocation = (String) document.get("user_primarylocation");
                                                current_user.secondarylocation = (String) document.get("user_secondarylocation");

                                                break;
                                            }
                                        } else {
                                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                i.putExtra("isAdmin", isAdmin);
                                progBar.setVisibility(View.INVISIBLE);
                                startActivity(i);
                            } else {
                                progBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

}

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            return;
        }


    }

}