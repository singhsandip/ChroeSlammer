package com.omninos.chroeslammer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.omninos.chroeslammer.Interface.RegisterApi;
import com.omninos.chroeslammer.Models.Register_Response_Model;
import com.omninos.chroeslammer.Models.Register_Result;
import com.omninos.chroeslammer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout loginLayout,registerLT;
    private String nameStr,emailStr,passStr,confPassStr,phnStr;
    private EditText nameET,emailET,passwordET,confPassET,phoneET;
    private String token = null;
    private ProgressDialog progressdialog;
    private Register_Response_Model register_response_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_screen);


        init();
    }

    private void init() {

        register_response_model = new Register_Response_Model();
        progressdialog = new ProgressDialog(this);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        registerLT = (LinearLayout) findViewById(R.id.registerLT);
        nameET = (EditText) findViewById(R.id.nameET);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        confPassET = (EditText) findViewById(R.id.confPassET);
        phoneET = (EditText) findViewById(R.id.phoneET);

        loginLayout.setOnClickListener(this);
        registerLT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.loginLayout:

                Intent loginIntent = new Intent(RegisterScreen.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();

                break;

            case R.id.registerLT:

                token = SharedPrefManager.getInstance(this).getDeviceToken();
                nameStr = nameET.getText().toString();
                emailStr = emailET.getText().toString();
                phnStr = phoneET.getText().toString();
                passStr = passwordET.getText().toString();
                confPassStr = confPassET.getText().toString();

                if (token == null)
                {
                   // Toast.makeText(this, "token not generated", Toast.LENGTH_SHORT).show();
                }
                if(nameStr.equalsIgnoreCase(""))
                {
                    nameET.setError("Enter your name");
                }
                else if(emailStr.equalsIgnoreCase("")){
                    emailET.setError("Enter your email");
                }
                else if(phnStr.equalsIgnoreCase("") ){
                    passwordET.setError("Enter your phone Number");
                }
                else if(passStr.equalsIgnoreCase("") ){
                    passwordET.setError("Enter your password");
                }
                else if(confPassStr.equalsIgnoreCase("")){
                    confPassET.setError("Enter your confirm password");
                }
                else if(!passwordET.getText().toString().equals(confPassET.getText().toString())) {

                    Toast.makeText(RegisterScreen.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
                else if(passStr.length() < 6 || confPassStr.length() < 6){
                    passwordET.setError("You must have six characters in your password");
                }
                else {

                  boolean check =   ValidEmail();
                    if (check)
                    {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                        progressdialog.setCancelable(false);
                        progressdialog.setMessage("Registering...");
                        progressdialog.show();
                        //RestAdapter retrofit = new RestAdapter.Builder().setEndpoint("http://omninos.com").build();
                        RegisterApi registerApi = Retrofit_Instance.getRetrofit().create(RegisterApi.class);
                        registerApi.insertUser(emailStr, passStr, nameStr, "android", token, new Callback<Register_Response_Model>() {



                            @Override
                            public void success(Register_Response_Model register_response_model, Response response) {


                                    String success = register_response_model.getSuccess();
                                    String message = register_response_model.getMessage();
                                    if (success.matches("True")) {



                                            Register_Result register_result = register_response_model.getResult();
                                            register_result.getRegId();
                                            register_result.getEmail();
                                            register_result.getUsername();
                                            register_result.getDeviceType();
                                            progressdialog.dismiss();
                                            Toast.makeText(RegisterScreen.this, "" + register_response_model.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                            Intent startActivity = new Intent(RegisterScreen.this, LoginActivity.class);
                                            startActivity(startActivity);
                                            finish();
                                        }

                                    else
                                    {
                                        Toast.makeText(RegisterScreen.this, ""+register_response_model.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }


                            @Override
                            public void failure(RetrofitError error)
                            {
                                progressdialog.dismiss();
                                Toast.makeText(RegisterScreen.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                Log.d("errooo", String.valueOf(error));
                            }
                        });
                    }
                }
                break;
        }
    }

    public boolean ValidEmail() {
        boolean check = false;
        String email11 = emailET.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email11.matches(emailPattern)) {
            check = true;
           // Intent registerIntent = new Intent(RegisterScreen.this,MainActivity.class);
            //startActivity(registerIntent);
           // finish();
        } else {
            emailET.setError("Invalid email address");
            Toast.makeText(RegisterScreen.this, "Invalid email address", Toast.LENGTH_SHORT).show();
        }
        return check;
    }
}
