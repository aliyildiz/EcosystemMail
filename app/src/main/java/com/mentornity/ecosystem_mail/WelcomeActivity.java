package com.mentornity.ecosystem_mail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
    Intent intent2,intent3;
    String pictureUrl, firstName, lastName;

    public static String URL = "url";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        profile_image = findViewById(R.id.profilePicture);
        details_text = findViewById(R.id.details);
        logout_text = findViewById(R.id.logoutText);


        Intent intent = getIntent();

        pictureUrl = (String) intent.getSerializableExtra("resimurl");
        firstName = (String) intent.getSerializableExtra("name");
        lastName = (String) intent.getSerializableExtra("lastname");

        details_text.setText(firstName+"\n"+lastName);




        final ImageView imageView = (ImageView) findViewById(R.id.profilePicture);
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

        intent2 = new Intent(this,InboxScreen.class);

        intent2.putExtra("urltoNav",pictureUrl);
        intent2.putExtra("firstNametoNav",firstName);
        intent2.putExtra("lastNametoNav",lastName);
        startActivity(intent2);
//        Bundle bundle =new Bundle();
//        bundle.putString(URL,pictureUrl);
//        intent2 = new Intent(this,navbarHeaderActivity.class);
//        intent2.putExtras(bundle);
//        startActivity(intent2);


        /*bundle.putString("isim",firstName);
        intent3=new Intent(this,InboxActivity.class);
        Intent [] intents={intent2,intent3};
        startActivities(intents);*/
    }
}
