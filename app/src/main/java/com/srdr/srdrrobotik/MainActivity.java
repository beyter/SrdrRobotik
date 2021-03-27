package com.srdr.srdrrobotik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.srdr.srdrrobotik.model.InstagramActivity;
import com.srdr.srdrrobotik.model.UserAuthInfo;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText userNameEdit;
    private EditText userPassEdit;
    ImageButton imageButtonWhatsapp;
    private ViewPager viewPager;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonWhatsapp=(ImageButton) findViewById(R.id.imageButtonWhatsapp);
        viewPager = (ViewPager) findViewById(R.id.pager_view);


        imageButtonWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("tel: 5549687737");
                Intent intent=new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });
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

    public void goToFacebook(View view) {
        startActivity(new Intent(this, FacebookActivity.class));}
    public void goToLinkedin(View view) {
        startActivity(new Intent(this, LinkedinActivity.class));}
    public void goToYoutube(View view) {
        startActivity(new Intent(this, YoutubeActivity.class));
    }
    public void goToInstagram(View view) {
        startActivity(new Intent(this, InstagramActivity.class));
    }
    public void goToSystem(View view) {
        startActivity(new Intent(this, SystemActivity.class));
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