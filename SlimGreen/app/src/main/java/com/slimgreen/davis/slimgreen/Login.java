package com.slimgreen.davis.slimgreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    private boolean userLogged;
    private EditText etUsername, etPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        userLogged = checkUserStatus();
        if(userLogged)
        {
            startActivity(new Intent(Login.this, SlimGreenService.class));
            finish();
        }else {

            etUsername = (EditText) findViewById(R.id.etUsername);
            etPassword = (EditText) findViewById(R.id.etPassword);
            btnLogin = (Button) findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap data = new HashMap();
                    data.put("txtUsername", etUsername.getText().toString());
                    data.put("txtPassword", etPassword.getText().toString());

                    PostResponseAsyncTask task = new PostResponseAsyncTask(Login.this, data, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            //ser verifica el inicio de sesión exitoso
                            if (!s.contains("failed")) {

                                //se guarda el estado de sesión y username
                                SharedPreferences pref = Login.this.getSharedPreferences(
                                        "Session Data", MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();
                                edit.putInt("LAST_VERSION_CODE", BuildConfig.VERSION_CODE);
                                edit.putBoolean("isLoggedIn", true);
                                edit.putString("username", s);
                                edit.commit();

                                //Se inicia la actividad
                                Intent myIntent = new Intent(Login.this, SlimGreenService.class);
                                startActivity(myIntent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, R.string.login_error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    task.execute("http://10.0.2.2/SlimGreen/login.php");

                }
            });
        }

    }


    protected boolean checkUserStatus(){
        boolean isLoggedIn ;
        Context context = getApplicationContext();
        SharedPreferences pref = context.getSharedPreferences("Session Data", MODE_PRIVATE);
        isLoggedIn = pref.getBoolean("isLoggedIn", false);
        return isLoggedIn ;
    }

}
