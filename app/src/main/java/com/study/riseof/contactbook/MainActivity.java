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

public class MainActivity extends AppCompatActivity implements SeekBarFragment.SeekBarProgressListener, AlphabetRecyclerViewAdapter.AdapterLetterClickListener{

    private final String EMPTY_STRING = "";
    private final String BUTTON_ALL = "All";

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
        database = contactsBaseSQLiteHelper.getWritableDatabase();
   //     database.delete(ContactsBaseSQLiteHelper.TABLE_CONTACTS, null, null);
     // database.close();
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
/*
    private void replaceFragments(){
        contactListFragment = new ContactListFragment();
        contactInfoFragment = new ContactInfoFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_contact_list,contactListFragment);
        fragmentTransaction.replace(R.id.frame_contact_info,contactInfoFragment);
        fragmentTransaction.commit();
    }*/

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
        database = contactsBaseSQLiteHelper.getWritableDatabase();
        Cursor cursorQuery;

        cursorQuery = database.rawQuery(queryByFirstLetterFromLastName(letter), null );
        ArrayList<String> selectedContacts = getSelectedContactsList(cursorQuery);
        contactListFragment.setContacts(selectedContacts);
        contactListFragment.setContactArrayAdapter();
        cursorQuery.close();
        database.close();
    }

    private String queryByFirstLetterFromLastName(String letter){
        if(letter.equals(BUTTON_ALL)){
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                    " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                    " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
        }else if(letter.equals("__")){
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                    " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                    " WHERE " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME +
                    " LIKE " + "'" + EMPTY_STRING + "'" +
                    " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
        }  else {
        return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME +
                " LIKE " + "'" + letter + "%'" +
                " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
    }
    }

    private ArrayList<String> getSelectedContactsList(Cursor cursor){
        ArrayList<String> contactsList = new ArrayList();
        String oneContact=EMPTY_STRING;
        if(cursor.moveToFirst()){
            do{
                oneContact = getOneContactFromCursor(cursor);
                contactsList.add(oneContact);
            }
            while(cursor.moveToNext());
        }
        return contactsList;
    }

    // как тут обойтись без цифр? Под каждый запрос делать ENUM?
    private String getOneContactFromCursor(Cursor cursor){
        String contact=EMPTY_STRING;
        String firstName = cursor.getString(1);
        String lastName = cursor.getString(0);
        String secondNameInitialLetter = cursor.getString(2);
        String patronymicInitialLetter = cursor.getString(3);
        contact =lastName +" "+ firstName +" "+ secondNameInitialLetter +" "+ patronymicInitialLetter;
        return contact;
    }


}
