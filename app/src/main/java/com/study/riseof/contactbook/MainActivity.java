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

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.lbl_title)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.get().load(R.drawable.book).into(imageContactBook);


      //  mPicasso.load(getItem(position)).resizeDimen(R.dimen.image_size, R.dimen.image_size). centerInside().into(imageView);

      /*  linkToContactsMainFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ContactsMainActivity.class);
                startActivity(intent);
            }
        });*/

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

    @OnClick(R.id.lbl_title)
    public void onClick2() {
        Toast.makeText(this, "Title", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_my_contacts, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.menu_item_contacts_add_contact :
                Toast.makeText(this, "first", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_contacts_back_to_main_activity :
                Toast.makeText(this, "second", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_contacts_quit:
                Intent minimizeApp = new Intent(Intent.ACTION_MAIN);
                minimizeApp.addCategory(Intent.CATEGORY_HOME);
                minimizeApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(minimizeApp);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
