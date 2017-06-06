package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.omninos.chroeslammer.Helper.Check_Internet_Connection;
import com.omninos.chroeslammer.Helper.Retrofit_Instance;
import com.omninos.chroeslammer.Helper.SharedPrefManager;
import com.omninos.chroeslammer.Interface.Login_Api;
import com.omninos.chroeslammer.Models.Login_Response_Model;
import com.omninos.chroeslammer.Models.Login_Result;
import com.omninos.chroeslammer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout registerLT,loginLT;
    private String loginEmail,loginPassword;
    private EditText userNameET,passwordET;
    private ProgressDialog progressdialog;
    private List<Login_Result> login_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);




     //   String token = SharedPrefManager.getInstance(this).getDeviceToken();
       // Toast.makeText(this, ""+token, Toast.LENGTH_SHORT).show();


        init();
    }

    private void init() {

        login_results = new ArrayList<>();
        progressdialog = new ProgressDialog(this);
        registerLT = (LinearLayout) findViewById(R.id.registerLT);
        loginLT = (LinearLayout) findViewById(R.id.loginLT);
        userNameET = (EditText) findViewById(R.id.userNameET);
        passwordET = (EditText) findViewById(R.id.passwordET);

        registerLT.setOnClickListener(this);
        loginLT.setOnClickListener(this);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.registerLT:

                Intent registerIntent = new Intent(LoginActivity.this,RegisterScreen.class);
                startActivity(registerIntent);
                finish();

                break;

            case R.id.loginLT:

                if (isNetworkAvailable())
                {

                    loginEmail = userNameET.getText().toString();
                    loginPassword = passwordET.getText().toString();

                    if(loginEmail.equalsIgnoreCase(""))
                    {
                        userNameET.setError("Enter UserName");
                    }
                    else if(loginPassword.equalsIgnoreCase(""))
                    {

                        passwordET.setError("Enter Password");
                    }
                    else {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                        progressdialog.setCancelable(false);
                        progressdialog.setMessage("Loging In..");
                        progressdialog.show();

                        String token = SharedPrefManager.getInstance(this).getDeviceToken();

                        Login_Api login_api = Retrofit_Instance.getRetrofit().create(Login_Api.class);
                        login_api.userLogin(loginEmail, loginPassword, token, new Callback<Login_Response_Model>() {


                            @Override
                            public void success(Login_Response_Model login_response_model, Response response) {



                                String s =login_response_model.getSuccess();
                                String m= login_response_model.getMessage();
                                login_results = login_response_model.getResult();
                                String id = login_results.get(login_results.size() - 1).getId();
                                String username = login_results.get(login_results.size() - 1).getUsername();
                                if (! id.isEmpty() && !username.isEmpty())
                                {
                                    SharedPrefManager.getInstance(LoginActivity.this).setUserId(id);
                                  //  ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                    //NetworkInfo nf = cn.getActiveNetworkInfo();


                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();

                                   // SharedPrefManager.getInstance(LoginActivity.this).setUserName(username);

                                }

                                /*login_results.get(login_results.size() - 1).getDeviceType();
                               String check =  login_results.get(login_results.size() - 1).getId();
                                Log.d("iddddddd",check);

                                String em = login_results.get(login_results.size() - 1).getEmail();
                                login_results.get(login_results.size() - 1).getUsername();
                                String reg =  login_results.get(login_results.size() - 1).getRegId();*/

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                progressdialog.dismiss();
                                Toast.makeText(LoginActivity.this, "log in failed", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }
                else
                {
                    Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
