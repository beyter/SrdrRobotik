package com.srdr.srdrrobotik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.srdr.srdrrobotik.model.User;
import com.srdr.srdrrobotik.model.UserAuthInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    private EditText nameEdit;
    private EditText surnameEdit;
    private EditText passwordEdit;
    private EditText phoneEdit;
    private EditText emailEdit;


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        nameEdit = findViewById(R.id.edt_name);
        surnameEdit = findViewById(R.id.edt_surname);
        passwordEdit = findViewById(R.id.edt_password);
        phoneEdit = findViewById(R.id.edt_phone);
        emailEdit = findViewById(R.id.edt_email);
    }

    public void SignUp(View view) {
        UserAuthInfo user = readUserInfo();
        if (!validate(user.getEmail(), user.getPassword())) {
            showToast("Email veya şifre validasyona uygun değil");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(authResult -> {
                    createUser(authResult.getUser().getUid());
                    showToast("Kayıt başarılı");
                    startActivity(new Intent(this, MainActivity.class));
                })
                .addOnFailureListener(failure -> showToast("Kayıt başarısız"));
    }

    private void createUser(String uid) {
        firestore.collection("Users")
                .document(uid).set(readUser(uid))
                .addOnSuccessListener(success -> showToast("Üye başarı ile oluşturuldu"))
                .addOnFailureListener(failure -> showToast("Kayıt başarısız"));
    }

    private User readUser(String uid) {
        User user = new User();
        user.setUUID(uid);
        user.setName(nameEdit.getText().toString());
        user.setSurname(surnameEdit.getText().toString());
        user.setPhoneNumber(phoneEdit.getText().toString());
        user.setEmail(emailEdit.getText().toString());
        return user;
    }

    private UserAuthInfo readUserInfo() {
        return new UserAuthInfo(
                emailEdit.getText().toString(),
                passwordEdit.getText().toString()
        );
    }

    private boolean validate(String email, String pass) {
        return true;
       /* return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find() &&
                VALID_PASSWORD_REGEX.matcher(pass).find();*/

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}