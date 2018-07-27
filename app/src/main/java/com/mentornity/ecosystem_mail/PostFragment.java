package com.mentornity.ecosystem_mail;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostFragment extends Fragment{
    String pictureUrl;
    String firstName;
    String lastName;
    SharedPreferences sharedPreferences;
    LinearLayout h_layout_post,ll_post;
    Button button;
    String[] array = {"JOB", "GENDER", "AGE", "SKILLS", "LOCATION"};
    String[][] child = {{"ENGINEER", "DOCTOR", "POLICE"}, {"MALE", "FEMALE"}, {"0-18", "18-24"}, {"C", "C++", "JAVA", "PYTHON"}, {"ISTANBUL", "ANKARA", "ADANA"}};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Menu1");
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_post, container, false);
        TextView textView = view.findViewById(R.id.postName);
        final ImageView imageView = view.findViewById(R.id.postPicture);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());

        pictureUrl = sharedPreferences.getString("resimurl","resim");
        firstName = sharedPreferences.getString("isim","isim");
        lastName = sharedPreferences.getString("soyisim","soyisim");

        textView.setText(firstName+" "+lastName);

        Picasso.with(getContext()).load(pictureUrl)
                .resize(150, 150)
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

        int a = array.length;

        h_layout_post = view.findViewById(R.id.h_layoutPost);


        try {
            for (int i=0;i<=a;i++){
                ll_post=new LinearLayout(getContext());
                ll_post.setOrientation(LinearLayout.VERTICAL);
                h_layout_post.addView(ll_post);
                TextView textView1 = new TextView(getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                textView1.setId(i);
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView1.setTypeface(Typeface.create("nunito",Typeface.NORMAL));
                ll_post.addView(textView1);
                button = new Button(getContext());
                button.setLayoutParams(new LinearLayout.LayoutParams(350, 200));
                button.setId(i);
                button.setTextColor(getResources().getColor(R.color.white));
                button.setBackgroundResource(R.drawable.createbutton_blue);
                button.setTypeface(Typeface.create("nunito-bold",Typeface.BOLD));
                String item = "";
                for(int j=0;j<child[i].length;j++){
                    item=item+child[i][j];
                    if (j!=child[i].length-1){
                        item=item+", ";
                    }
                }
                button.setText(item);
                ll_post.addView(button);
            }

        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.post_menu,menu);
    }

}
