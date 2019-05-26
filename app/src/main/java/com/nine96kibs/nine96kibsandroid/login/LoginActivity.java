package com.nine96kibs.nine96kibsandroid.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nine96kibs.nine96kibsandroid.CommonResult;
import com.nine96kibs.nine96kibsandroid.MainActivity;
import com.nine96kibs.nine96kibsandroid.R;
import com.nine96kibs.nine96kibsandroid.vo.AccountInfoVO;
import com.nine96kibs.nine96kibsandroid.vo.AccountVO;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signinButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        signinButton = findViewById(R.id.signin);

        signinButton.setOnClickListener(v -> {
            signin();
        });

        loginButton.setOnClickListener(v -> {
            login();
        });
    }


    private void signin() {
        new Thread(() -> {
            try {
                Gson gson = new Gson();
                String userName = String.valueOf(usernameEditText.getText());
                String password = String.valueOf(passwordEditText.getText());
                if (userName.length() < 8 || password.length() < 6) {
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "输入过短，请重新尝试", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    return;
                }
                AccountVO accountVO = new AccountVO(userName, password);
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .readTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(accountVO));
                Request request = new Request.Builder().url("http://47.100.97.17:8848/account/register").post(requestBody).build();
                Response response = httpClient.newCall(request).execute();
                Looper.prepare();
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "注册失败，请重新尝试", Toast.LENGTH_SHORT).show();
                } else {
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    Toast.makeText(LoginActivity.this, "注册成功，请登陆", Toast.LENGTH_SHORT).show();
                }
                Looper.loop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void login() {
        new Thread(() -> {
            try {
                Gson gson = new Gson();
                String userName = String.valueOf(usernameEditText.getText());
                String password = String.valueOf(passwordEditText.getText());
                if (userName.length() < 8 || password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "输入过短，请重新尝试", Toast.LENGTH_SHORT).show();
                    return;
                }
                AccountVO accountVO = new AccountVO(userName, password);
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(6, TimeUnit.SECONDS)
                        .connectTimeout(6, TimeUnit.SECONDS)
                        .build();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(accountVO));
                Request request = new Request.Builder().url("http://47.100.97.17:8848/account/login").post(requestBody).build();
                Response response = httpClient.newCall(request).execute();
                CommonResult commonResult = gson.fromJson(response.body().string(), CommonResult.class);
                AccountInfoVO accountInfoVO = gson.fromJson(gson.toJson(commonResult.getData()), AccountInfoVO.class);
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putInt("id", accountInfoVO.getId());
                editor.putString("name", accountInfoVO.getUsername());
                editor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
