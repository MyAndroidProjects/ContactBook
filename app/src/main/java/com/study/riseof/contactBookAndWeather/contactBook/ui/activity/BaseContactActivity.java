package com.study.riseof.contactBookAndWeather.contactBook.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseContactActivity extends AppCompatActivity {
    protected final String EMPTY_STRING = "";
    protected final int EMPTY_INDEX = -1;
    protected final String POINT = ".";
    protected final int INITIAL_LETTER_INDEX = 0;

    protected int selectedContactId = EMPTY_INDEX;
    protected String selectedLetter = EMPTY_STRING;

    abstract int getActivityLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        ButterKnife.bind(this);
        getArgs();
    }

    protected void getArgs(){
        selectedContactId = getIntent().getIntExtra("selectedContactId", EMPTY_INDEX);
        if (getIntent().getStringExtra("selectedLetter") == null) {
            selectedLetter = EMPTY_STRING;
        } else {
            selectedLetter = getIntent().getStringExtra("selectedLetter");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedContactId", selectedContactId);
        outState.putString("selectedLetter", selectedLetter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedContactId = savedInstanceState.getInt("selectedContactId", EMPTY_INDEX);
        selectedLetter = savedInstanceState.getString("selectedLetter", EMPTY_STRING);

    }

}
