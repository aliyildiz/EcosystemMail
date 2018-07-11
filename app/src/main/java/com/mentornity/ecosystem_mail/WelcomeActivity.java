package com.mentornity.ecosystem_mail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class WelcomeActivity extends AppCompatActivity {

    ImageView profile_image;
    TextView details_text,logout_text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        profile_image = findViewById(R.id.profilePicture);
        details_text = findViewById(R.id.details);
        logout_text = findViewById(R.id.logoutText);


        Intent intent = getIntent();

        String pictureUrl = (String) intent.getSerializableExtra("resimurl");
        String firstName = (String) intent.getSerializableExtra("name");
        String lastName = (String) intent.getSerializableExtra("lastname");
        String e_mail = (String) intent.getSerializableExtra("mail");


        Picasso.with(getApplicationContext()).load(pictureUrl).into(profile_image);
        details_text.setText(firstName+"\n"+lastName);

    }
}
