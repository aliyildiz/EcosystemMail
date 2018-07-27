package com.mentornity.ecosystem_mail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.irshulx.Editor;
import com.github.irshulx.EditorListener;
import com.github.irshulx.models.EditorTextStyle;

import java.util.ArrayList;
import java.util.List;

public class CreateMail extends AppCompatActivity{

    Editor editor;
    boolean[][] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String email;

    Button button;
    List<Button> buttonList = new ArrayList<>();
    LinearLayout h_layout, createLayout;
    String[] array = {"JOB", "GENDER", "AGE", "SKILLS", "LOCATION"};
    String[][] child = {{"ENGINEER", "DOCTOR", "POLICE"}, {"MALE", "FEMALE"}, {"0-18", "18-24"}, {"C", "C++", "JAVA", "PYTHON"}, {"ISTANBUL", "ANKARA", "ADANA"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mail);
        editor = findViewById(R.id.editor);
        setUpEditor();
        int a = array.length;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        email = sharedPreferences.getString("email","email");
        TextView emailText = findViewById(R.id.email);
        emailText.setText(email);


        Button sendButton = findViewById(R.id.sendButton);
        final TextView subject = findViewById(R.id.subject);
        final TextView email = findViewById(R.id.email);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(email.getText().toString(),subject.getText().toString(),editor.getContentAsHTML());
            }
        });


        h_layout = findViewById(R.id.h_layout);
        createLayout = findViewById(R.id.createLayout);
        try {
            for (int i = 0; i <= a; i++) {
                LinearLayout ll = new LinearLayout(CreateMail.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                h_layout.addView(ll);
                button = new Button(CreateMail.this);
                button.setLayoutParams(new LinearLayout.LayoutParams(350, 200));
                button.setId(i);
                button.setText("SELECT");
                button.setBackgroundResource(R.drawable.select_category_button);
                ll.addView(button);
                buttonList.add(button);
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

                                    if (!isChecked){
                                        if (!mUserItems.contains(which)){
                                            mUserItems.add(which);
                                        }else {
                                            mUserItems.remove(which);
                                        }
                                    }

                                }
                            });

                            mBuilder.setCancelable(true);
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

    private void sendMail(String email, String subject, String messageBody) {

        Spanned message;
        message= Html.fromHtml(messageBody);

        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("message/rfc822");
        mailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT,message);
        startActivity(mailIntent);

    }



    private void setUpEditor() {
        findViewById(R.id.action_h1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H1);
            }
        });

        findViewById(R.id.action_h2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H2);
            }
        });

        findViewById(R.id.action_h3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H3);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.BOLD);
            }
        });

        findViewById(R.id.action_Italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.ITALIC);
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.INDENT);
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.OUTDENT);
            }
        });

        findViewById(R.id.action_bulleted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertList(false);
            }
        });

        findViewById(R.id.action_unordered_numbered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertList(true);
            }
        });

        findViewById(R.id.action_hr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertDivider();
            }
        });

//        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editor.openImagePicker();
//            }
//        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertLink();
            }
        });

//        findViewById(R.id.action_map).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editor.insertMap();
//            }
//        });

//        findViewById(R.id.action_erase).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editor.clearAllContents();
//            }
//        });
        //editor.dividerBackground=R.drawable.divider_background_dark;
        //editor.setFontFace(R.string.fontFamily__serif);
//        Map<Integer, String> headingTypeface = getHeadingTypeface();
//        Map<Integer, String> contentTypeface = getContentface();
//        editor.setHeadingTypeface(headingTypeface);
//        editor.setContentTypeface(contentTypeface);
        editor.setDividerLayout(R.layout.tmpl_divider_layout);
        editor.setEditorImageLayout(R.layout.tmpl_image_view);
        editor.setListItemLayout(R.layout.tmpl_list_item);
        //editor.StartEditor();
        editor.setEditorListener(new EditorListener() {
            @Override
            public void onTextChanged(EditText editText, Editable text) {
                // Toast.makeText(EditorTestActivity.this, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpload(Bitmap image, String uuid) {
                Toast.makeText(CreateMail.this, uuid, Toast.LENGTH_LONG).show();
                editor.onImageUploadComplete("http://www.videogamesblogger.com/wp-content/uploads/2015/08/metal-gear-solid-5-the-phantom-pain-cheats-640x325.jpg", uuid);
                // editor.onImageUploadFailed(uuid);
            }
        });
        editor.render();
    }
}





