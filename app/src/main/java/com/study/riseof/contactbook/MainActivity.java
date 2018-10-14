package com.study.riseof.contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
