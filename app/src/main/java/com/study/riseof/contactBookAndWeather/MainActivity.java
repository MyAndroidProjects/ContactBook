package com.study.riseof.contactBookAndWeather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.weather.ui.activity.WeatherForecastActivity;
import com.study.riseof.contactBookAndWeather.contactBook.ui.activity.ContactsMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame_link_to_contacts_main)
    FrameLayout linkToContactsMainFrame;
    @BindView(R.id.frame_link_to_weather_main)
    FrameLayout linkToWeatherMainFrame;
    @BindView(R.id.image_contact_book)
    ImageView imageContactBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.get().load(R.drawable.book).into(imageContactBook);
    }

    @OnClick(R.id.frame_link_to_contacts_main)
    public void onClickLinkToContacts() {
        Intent intent = new Intent(this, ContactsMainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.frame_link_to_weather_main)
    public void onClickLinkToWeather() {
        Intent intent = new Intent(this, WeatherForecastActivity.class);
        startActivity(intent);
    }
}
