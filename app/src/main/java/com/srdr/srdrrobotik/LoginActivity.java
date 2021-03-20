package com.srdr.srdrrobotik;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.srdr.srdrrobotik.model.InstagramActivity;

public class LoginActivity extends AppCompatActivity {

    WebView webView;
    ImageView btnArama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnArama=(ImageView)findViewById(R.id.btnArama);

        btnArama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("tel: 5549687737");
                Intent intent=new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });
    }


    public void goToFacebook(View view) {
        startActivity(new Intent(this, FacebookActivity.class));}

    public void goToLinkedin(View view) {
        startActivity(new Intent(this, LinkedinActivity.class));}
    public void goToInstagram(View view) {
        startActivity(new Intent(this, InstagramActivity.class));
    }
    public void goToYoutube(View view) {
        startActivity(new Intent(this, YoutubeActivity.class));
    }
    public void goToWeb(View view) {
        startActivity(new Intent(this, WebActivity.class));
    }
    public void goToSystem(View view) {
        startActivity(new Intent(this, SystemActivity.class));
    }
}