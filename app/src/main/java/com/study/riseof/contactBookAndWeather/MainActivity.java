package com.study.riseof.contactBookAndWeather;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.study.riseof.contactBookAndWeather.weather.ui.activity.WeatherForecastActivity;
import com.study.riseof.contactBookAndWeather.contactBook.ui.activity.ContactsMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.animation_image_view)
    ImageView animationView;

    private AnimationDrawable animationDrawable;
    ImageView.ScaleType scaleType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animationDrawable =(AnimationDrawable) animationView.getBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        animationDrawable.start();
    }

    @OnClick(R.id.image_contact_book)
    public void onClickLinkToContacts() {
        Intent intent = new Intent(this, ContactsMainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.animation_link_to_weather)
    public void onClickLinkToWeather() {
        Intent intent = new Intent(this, WeatherForecastActivity.class);
        startActivity(intent);
    }
}
