package com.nine96kibs.nine96kibsandroid.recite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nine96kibs.nine96kibsandroid.R;

import java.util.Objects;

public class ReciteActivity extends AppCompatActivity {

    View view;
    Button haveDoneButton;
    Button almostDoneButton;
    Button notDoneButton;
    TextView question;
    TextView reciteSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        View view = findViewById(R.id.content_recite);
        view.setOnClickListener(v -> {
            System.out.println("p");
        });
        haveDoneButton = findViewById(R.id.button_have_done);
        haveDoneButton.setOnClickListener(v -> {

        });
        almostDoneButton = findViewById(R.id.button_almost_done);
        almostDoneButton.setOnClickListener(v -> {

        });
        notDoneButton = findViewById(R.id.button_not_done);
        notDoneButton.setOnClickListener(v -> {
            System.out.println("c");
        });
        question = findViewById(R.id.question);
        reciteSource = findViewById(R.id.recite_source);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void clickHaveDone() {
    }

    private void clickAlmostDone() {

    }

    private void clickNotDone() {

    }

    private String makeAnswer(String question, String answer) {
        int start = question.indexOf('\u25A1');
        int end = question.indexOf('\u25A1');
        return question.substring(0, start) + answer + question.substring(end + 1);
    }

}
