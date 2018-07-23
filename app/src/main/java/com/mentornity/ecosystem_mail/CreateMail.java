package com.mentornity.ecosystem_mail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMail extends AppCompatActivity{

    Button mOrder;
    TextView mItemSelected;
    String []listItems;
    boolean[][] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    String item;
    Button btn;
    List<Button> buttonList = new ArrayList<>();
    LinearLayout h_layout, createLayout;
    String[] array = {"JOB", "GENDER", "AGE", "SKILLS", "LOCATION"};
    String[][] child = {{"ENGINEER", "DOCTOR", "POLICE"}, {"MALE", "FEMALE"}, {"0-18", "18-24"}, {"C", "C++", "JAVA", "PYTHON"}, {"ISTANBUL", "ANKARA", "ADANA"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mail);

        int a = array.length;
        System.out.println("eleman" + a);


        h_layout = findViewById(R.id.h_layout);
        createLayout = findViewById(R.id.createLayout);
        try {
            for (int i = 0; i <= a; i++) {
                LinearLayout ll = new LinearLayout(CreateMail.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                h_layout.addView(ll);
                btn = new Button(CreateMail.this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(350, 200));
                btn.setId(i);
                btn.setText(array[i]);
                btn.setBackgroundResource(R.drawable.select_category_button);
                ll.addView(btn);
                buttonList.add(btn);
                checkedItems = new boolean[array.length][1000];



                for(final Button btn : buttonList){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(CreateMail.this);
                            mBuilder.setTitle(array[btn.getId()]);
                            mBuilder.setMultiChoiceItems(child[btn.getId()],checkedItems[btn.getId()],new DialogInterface.OnMultiChoiceClickListener(){


                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    try{

                                    }catch (Exception e2){
                                        System.out.println("Error2:"+e2.getMessage());
                                    }
                                    if (!isChecked){
                                        if (!mUserItems.contains(which)){
                                            mUserItems.add(which);
                                        }else {
                                            mUserItems.remove(which);
                                        }
                                    }
                                }
                            });

                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String item = "";
                                    for (int i=0;i<mUserItems.size();i++){
                                        item=item+array[mUserItems.get(i)];
                                        if (i!=mUserItems.size()-1){
                                            item=item+",";
                                        }
                                    }
                                }
                            });
                            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            mBuilder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (int i=0;i<checkedItems.length;i++){
                                        checkedItems[btn.getId()][i]=false;
                                        mUserItems.clear();
                                    }
                                }
                            });
                            android.app.AlertDialog alertDialog = mBuilder.create();
                            alertDialog.show();

                        }
                    });
                }

                TextView txt = new TextView(CreateMail.this);
                txt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                txt.setText(array[i]);
                ll.addView(txt);
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }


//    @Override
//    public void onClick(View v) {
//
//        btn.setId(v.getId());
//
//        listItems=child[btn.getId()];
//
//        for (int i=0;i<child[btn.getId()].length;i++){
//
//            System.out.println("check01"+listItems[i]);
//        }
//
//        checkedItems= new boolean[child[btn.getId()].length];
//        System.out.println("check-1:"+checkedItems.toString());
//
//        System.out.println("child eleman:"+listItems);
//
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
//        mBuilder.setTitle(array[btn.getId()]);
//        mBuilder.setMultiChoiceItems(child[btn.getId()], checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                try{
//                    if (isChecked) {
//                        if (! mUserItems.contains(position)) {
//                            mUserItems.add(position);
//                            System.out.println("muser:"+mUserItems);
//
//                        } else {
//                            mUserItems.remove(position);
//                        }
//                    }
//                }catch (Exception e){
//                    System.out.println("builder hatası1:"+e.getMessage());
//                }
//
//            }
//        });
//        mBuilder.setCancelable(false);
//        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                item = "";
//                try{
//                    for (int i=0;i<mUserItems.size();i++){
//                        item=item+child[btn.getId()][mUserItems.get(i)];
//                        if (i!=mUserItems.size()-1){
//                            item = item + ",";
//                        }
//
//                        System.out.println("item:"+item);
//                    }
//                }catch (Exception e){
//                    System.out.println("deneme2:"+e.getMessage());
//                }
//
//                System.out.println("deneme:"+mUserItems);
//                System.out.println("secimler:"+item);
//            }
//        });
//
//        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//
//        mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                for(int i=0;i<checkedItems.length;i++){
//                    checkedItems[i]=false;
//                    mUserItems.clear();
//                    System.out.println("temizlendi.");
//                }
//            }
//        });
//
//        AlertDialog mDialog = mBuilder.create();
//        mDialog.show();
//        //mBuilder.setCancelable(true);
//
//    }
}


