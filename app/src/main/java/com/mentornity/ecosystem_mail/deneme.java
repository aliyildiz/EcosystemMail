package com.mentornity.ecosystem_mail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.squareup.picasso.Picasso;

public class deneme extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);

        imageView = findViewById(R.id.pp);
        textView = findViewById(R.id.details);
        button = findViewById(R.id.button);

        Intent intent = getIntent();

        //Bundle bundle = intent.getExtras();

        String pictureUrl = (String) intent.getSerializableExtra("resimurl");
        System.out.println(pictureUrl);
        String firstName = (String) intent.getSerializableExtra("name");
        String lastName = (String) intent.getSerializableExtra("lastname");
        String e_mail = (String) intent.getSerializableExtra("mail");

        //String pictureUrl = bundle.getString("urlKey");

        Picasso.with(getApplicationContext()).load(pictureUrl).into(imageView);
        textView.setText(firstName+"\n"+lastName+"\n"+e_mail);



    }

}
