package com.nine96kibs.nine96kibsandroid.recite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nine96kibs.nine96kibsandroid.CommonResult;
import com.nine96kibs.nine96kibsandroid.R;
import com.nine96kibs.nine96kibsandroid.vo.ReciteLearnChoice;
import com.nine96kibs.nine96kibsandroid.vo.ReciteToLearn;

import java.io.IOError;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReciteActivity extends AppCompatActivity {

    Button haveDoneButton;
    Button almostDoneButton;
    Button notDoneButton;
    TextView question;
    TextView reciteSource;

    ReciteToLearn reciteToLearn;

    int userId;
    int taskId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        View view = findViewById(R.id.content_recite);
        view.setOnClickListener(v -> {
            question.setText(makeAnswer(reciteToLearn.getTopic(), reciteToLearn.getAnswer()));
        });

        haveDoneButton = findViewById(R.id.button_have_done);
        haveDoneButton.setOnClickListener(v -> clickHaveDone());
        almostDoneButton = findViewById(R.id.button_almost_done);
        almostDoneButton.setOnClickListener(v -> clickAlmostDone());
        notDoneButton = findViewById(R.id.button_not_done);
        notDoneButton.setOnClickListener(v -> clickNotDone());
        question = findViewById(R.id.question);
        reciteSource = findViewById(R.id.recite_source);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", 0);

        nextRecite();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void clickHaveDone() {
        sendChoice(new ReciteLearnChoice(reciteToLearn.getReciteId(), userId, 2));
    }

    private void clickAlmostDone() {
        sendChoice(new ReciteLearnChoice(reciteToLearn.getReciteId(), userId, 1));
    }

    private void clickNotDone() {
        sendChoice(new ReciteLearnChoice(reciteToLearn.getReciteId(), userId, 0));
    }

    private void sendChoice(ReciteLearnChoice learnChoice) {
        new Thread(() -> {
            try {
                Gson gson = new Gson();
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .readTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(learnChoice));
                Request request = new Request.Builder().url("http://47.100.97.17:8848/classic-poetry/normal/choose").post(requestBody).build();
                Response response = httpClient.newCall(request).execute();
                if (response.body() == null) {
                    throw new Exception();
                }
                CommonResult commonResult = gson.fromJson(response.body().string(), CommonResult.class);
                if (commonResult.getCode() != 200) {
                    this.runOnUiThread(() -> Toast.makeText(ReciteActivity.this, commonResult.getCode() + " " + commonResult.getMessage(), Toast.LENGTH_SHORT).show());
                }
                nextRecite();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                this.runOnUiThread(() -> Toast.makeText(ReciteActivity.this, "选择发送失败，请检查网络设置", Toast.LENGTH_LONG).show());
            }
        }).start();
    }

    private void nextRecite() {
        new Thread(() -> {
            try {
                Gson gson = new Gson();
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .readTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url("http://47.100.97.17:8848/classic-poetry/normal/learn-list?user-id=" + userId + "&task-id=" + taskId).get().build();
                Response response = httpClient.newCall(request).execute();
                if (response.body() == null) throw new Exception();
                CommonResult commonResult = gson.fromJson(response.body().string(), CommonResult.class);
                if (commonResult.getCode() != 200) {
                    this.runOnUiThread(() -> Toast.makeText(ReciteActivity.this, commonResult.getCode() + " " + commonResult.getMessage(), Toast.LENGTH_SHORT).show());
                }
                if (commonResult.getData() == "") {
                    this.runOnUiThread(() -> Toast.makeText(ReciteActivity.this, "没有更多要学习的题目了，换组任务吧", Toast.LENGTH_SHORT).show());
                } else {
                    reciteToLearn = gson.fromJson(gson.toJson(commonResult.getData()), ReciteToLearn.class);
                    this.runOnUiThread(() -> {
                        reciteToLearn.setTopic(reciteToLearn.getTopic().replace("/?", "\u25A1"));
                        question.setText(reciteToLearn.getTopic());
                        reciteSource.setText(reciteToLearn.getTopicSub());
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.d("InitialTest", "nextRecite: 8");
                this.runOnUiThread(() -> Toast.makeText(ReciteActivity.this, "题目接收失败，请检查网络设置", Toast.LENGTH_LONG).show());
            }
        }).start();
    }

    private String makeAnswer(String question, String answer) {
        if (question.contains("\u25A1")) {
            int start = question.indexOf('\u25A1');
            int end = question.lastIndexOf('\u25A1');
            return question.substring(0, start) + answer + question.substring(end + 1);
        }
        return question;
    }

}
