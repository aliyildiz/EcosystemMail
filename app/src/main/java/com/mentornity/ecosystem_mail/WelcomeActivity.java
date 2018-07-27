package com.mentornity.ecosystem_mail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class WelcomeActivity extends AppCompatActivity {

    ImageView profile_image;
    TextView details_text,logout_text;
    Intent intent;
    String pictureUrl, firstName, lastName;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        profile_image = findViewById(R.id.profilePicture);
        details_text = findViewById(R.id.details);
        logout_text = findViewById(R.id.logoutText);

        pictureUrl = sharedPreferences.getString("resimurl","resim");
        firstName = sharedPreferences.getString("isim","isim");
        lastName = sharedPreferences.getString("soyisim","soyisim");


        details_text.setText(firstName+" "+lastName);

        final ImageView imageView = findViewById(R.id.profilePicture);
        Picasso.with(WelcomeActivity.this).load(pictureUrl)
                .resize(100, 100)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        imageView.setImageDrawable(imageDrawable);
                    }
                    @Override
                    public void onError() {
                        imageView.setImageResource(R.drawable.logo);
                    }
                });

    }
    public void start(View view){

        intent = new Intent(this,InboxScreen.class);
        startActivity(intent);

    }
}
