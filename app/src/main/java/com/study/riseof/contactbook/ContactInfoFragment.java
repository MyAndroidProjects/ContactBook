package com.study.riseof.contactbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactInfoFragment extends Fragment {
    private Unbinder unbinder;

    @BindView(R.id.text_first_name) TextView firstNameText;
    @BindView(R.id.text_second_name) TextView secondNameText;
    @BindView(R.id.text_patronymic) TextView patronymicText;
    @BindView(R.id.text_last_name) TextView lastNameText;
    @BindView(R.id.text_mobile_phone) TextView mobilePhoneText;
    @BindView(R.id.text_home_phone) TextView homePhoneText;
    @BindView(R.id.text_personal_website) TextView personalWebsiteText;
    @BindView(R.id.text_e_mail) TextView eMailText;
    @BindView(R.id.text_flat) TextView flatText;
    @BindView(R.id.text_house) TextView houseText;
    @BindView(R.id.text_street) TextView streetText;
    @BindView(R.id.text_city) TextView cityText;
    @BindView(R.id.text_state) TextView stateText;
    @BindView(R.id.text_country) TextView countryText;
    @BindView(R.id.text_post_code) TextView postCodeText;

    ContactsBaseSQLiteHelper contactsBaseSQLiteHelper;
    SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(getContext());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setContactInfoById(int id) {

        database = contactsBaseSQLiteHelper.getReadableDatabase();
        Cursor cursorQuery;
        // как лучше делать запросы: передавать аргументы прямо в строку запроса, а в selectionArg ставить null
        // или ставить вопросительные знаки, а аргументы передавать в массив? (первый и второй вариант)
        cursorQuery = database.rawQuery(queryInformById(id), null );
     /* cursorQuery = database.rawQuery("SELECT * FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE "+ ContactsBaseSQLiteHelper.COLUMN_ID + "= ?",new String[]{String.valueOf(id)} ); */
        setContactInfoText(cursorQuery);
        cursorQuery.close();
        database.close();
    }
    private String queryInformById(int id){
        return " SELECT * FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE " + ContactsBaseSQLiteHelper.COLUMN_ID + " = '" + id + "'";
    }

    private void setContactInfoText(Cursor cursor){
        if(cursor.moveToFirst()){
            firstNameText.setText(cursor.getString(1));
            secondNameText.setText(cursor.getString(3));
            patronymicText.setText(cursor.getString(5));
            lastNameText.setText(cursor.getString(7));
            mobilePhoneText.setText(cursor.getString(9));
            homePhoneText.setText(cursor.getString(10));
            personalWebsiteText.setText(cursor.getString(11));
            eMailText.setText(cursor.getString(12));
            flatText.setText(cursor.getString(13));
            houseText.setText(cursor.getString(14));
            streetText.setText(cursor.getString(15));
            cityText.setText(cursor.getString(16));
            stateText.setText(cursor.getString(17));
            countryText.setText(cursor.getString(18));
            postCodeText.setText(cursor.getString(19));
        }
    }

}
