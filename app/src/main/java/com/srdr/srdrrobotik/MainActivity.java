package com.srdr.srdrrobotik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.srdr.srdrrobotik.model.UserAuthInfo;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText userNameEdit;
    private EditText userPassEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        userNameEdit = findViewById(R.id.edit_text_user_name);
        userPassEdit = findViewById(R.id.edit_text_pass);

    }

    public void SignIn(View view) {
        UserAuthInfo user = readUserInfo();

        firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(success -> {
                    showToast("Hoşgeldin");
                    startActivity(new Intent(this, LoginActivity.class));
                })
                .addOnFailureListener(failure -> {
                            showToast("Hatalı ");
                            showToast(failure.getMessage());
                        }
                );

    }

    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private UserAuthInfo readUserInfo() {
        return new UserAuthInfo(
                userNameEdit.getText().toString(),
                userPassEdit.getText().toString()
        );
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}