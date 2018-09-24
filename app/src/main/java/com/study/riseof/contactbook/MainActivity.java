package com.study.riseof.contactbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements SeekBarFragment.SeekBarProgressListener,
        AlphabetRecyclerViewAdapter.AdapterLetterClickListener,
        ContactListFragment.ContactListClickListener {

    @BindView(R.id.frame_contact_list)
    FrameLayout contactListFrame;
    @BindView(R.id.frame_contact_info)
    FrameLayout contactInfoFrame;

    ContactListFragment contactListFragment;
    ContactInfoFragment contactInfoFragment;
    ButtonPanelFragment buttonPanelFragment;
    AlphabetListFragment alphabetListFragment;

   private int maxSeekBar;

    public static final String TAG = "myLogs";

    ContactsBaseSQLiteHelper contactsBaseSQLiteHelper;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        maxSeekBar = getResources().getInteger(R.integer.seek_bar_maximum);
        addFragments();
        contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(this);

   //     database.delete(ContactsBaseSQLiteHelper.TABLE_CONTACTS, null, null);
     //    this.deleteDatabase(ContactsBaseSQLiteHelper.DATABASE_NAME);
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  this.deleteDatabase(ContactsBaseSQLiteHelper.DATABASE_NAME);
    }

    private void addFragments(){
        alphabetListFragment = new AlphabetListFragment();
        contactListFragment = new ContactListFragment();
        contactInfoFragment = new ContactInfoFragment();
        buttonPanelFragment = new ButtonPanelFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_alphabet_list, alphabetListFragment);
        fragmentTransaction.add(R.id.frame_contact_list, contactListFragment);
        fragmentTransaction.add(R.id.frame_contact_info, contactInfoFragment);
        fragmentTransaction.add(R.id.frame_button_panel, buttonPanelFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeSeekBarProgress(int progress) {
        LinearLayout.LayoutParams paramsContactListFrame = (LinearLayout.LayoutParams)contactListFrame.getLayoutParams();
        LinearLayout.LayoutParams paramsContactInfoFrame = (LinearLayout.LayoutParams)contactInfoFrame.getLayoutParams();
        paramsContactListFrame.weight=(maxSeekBar-progress);
        paramsContactInfoFrame.weight=progress;
        contactListFrame.setLayoutParams(paramsContactListFrame);
        contactInfoFrame.setLayoutParams(paramsContactInfoFrame);
    }

    @Override
    public void adapterLetterClick(String letter) {
        Log.d("myLog","нажата буква "+letter);
        contactListFragment.showContactsByFirstLetter(letter);
   }

    @Override
    public void onContactItemClick(int id) {
        contactInfoFragment.setContactInfoById(id);
    }
}
