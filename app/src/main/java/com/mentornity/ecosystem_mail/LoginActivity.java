package com.mentornity.ecosystem_mail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    String first_name,last_name,emailAddress,pictureURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent = new Intent(this,WelcomeActivity.class);

    }

    public void connectLinkedin(View view) {

        switch (view.getId()){
            case R.id.linkedin_login_btn:
                handleLogin();
                break;
        }
    }

    private void handleLogin(){
        LISessionManager.getInstance(getApplicationContext()).init(this, buildScope(), new AuthListener() {
            @Override
            public void onAuthSuccess() {
                // Authentication was successful.  You can now do
                // other calls with the SDK.
                fetchPersonalInfo();
            }

            @Override
            public void onAuthError(LIAuthError error) {
                // Handle authentication errors
                Log.e("Failed",error.toString());
            }
        }, true);
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE, Scope.R_EMAILADDRESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }

    private void fetchPersonalInfo(){

        final String url  = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,public-profile-url,picture-url,email-address,picture-urls::(originals))";

        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                // Success!
                JSONObject jsonobject = apiResponse.getResponseDataAsJson();
                try {
                    first_name = jsonobject.getString("firstName");
                    last_name = jsonobject.getString("lastName");
                    pictureURL = jsonobject.getString("pictureUrl");
                    emailAddress = jsonobject.getString("emailAddress");

                    intent.putExtra("resimurl",pictureURL);
                    intent.putExtra("name",first_name);
                    intent.putExtra("lastname",last_name);
                    intent.putExtra("mail",emailAddress);


                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onApiError(LIApiError liApiError) {
                // Error making GET request!
                Log.e("Failed to Login",liApiError.getMessage());


            }
        });
    }

}
