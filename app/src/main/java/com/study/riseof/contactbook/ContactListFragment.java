package com.study.riseof.contactbook;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;


import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.os.Build.ID;

public class ContactListFragment extends Fragment {
    private final String EMPTY_STRING = "";
    private final String BUTTON_ALL = "All";
    private Unbinder unbinder;

    @BindView(R.id.contact_list_view)
    ListView contactListView;

    ArrayList<String[]> contactsWithId = new ArrayList();
    ArrayList<AbbreviatedContact> contacts = new ArrayList();

    ContactListAdapter contactAdapter;
    View view;
    ContactListClickListener contactClickListener;

    ContactsBaseSQLiteHelper contactsBaseSQLiteHelper;
    SQLiteDatabase database;

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
        try {
            contactClickListener = (ContactListClickListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(getContext());
        setContactArrayAdapter();
        // как лучше ставить обработчик, ниже два варианта:
        AdapterView.OnItemClickListener abbreviatedContactListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                AbbreviatedContact abbreviatedContact = (AbbreviatedContact)parent.getItemAtPosition(position);
                contactClickListener.onContactItemClick(abbreviatedContact.getItemId());
            //    Log.d("myLog", "click name "+abbreviatedContact.getContactName()+ " position = " + position + ", id = " + id);
            }
        };
        contactListView.setOnItemClickListener(abbreviatedContactListener);

    /*    contactListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            AbbreviatedContact abbreviatedContact = (AbbreviatedContact)parent.getItemAtPosition(position);
            }
        });*/

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showContactsByFirstLetter(String letter){
        database = contactsBaseSQLiteHelper.getReadableDatabase();
        Cursor cursorQuery;

        cursorQuery = database.rawQuery(queryByFirstLetterFromLastName(letter), null );
        ArrayList<AbbreviatedContact> selectedContacts = getSelectedContactsList(cursorQuery);
        setContacts(selectedContacts);
        setContactArrayAdapter();
        cursorQuery.close();
        database.close();
    }

    public void setContacts(ArrayList<AbbreviatedContact> contacts) {
        Log.d("myLog","Контакты обновлены!");
        this.contacts = contacts;
    }

    public void setContactArrayAdapter(){
        contactAdapter = new ContactListAdapter(getContext(), R.layout.contact_list_item, contacts);
        contactListView.setAdapter(contactAdapter);
        Log.d("myLog","Адаптер установлен!");
    }

    private String queryByFirstLetterFromLastName(String letter){
        if(letter.equals(BUTTON_ALL)){
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                    " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                    " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
        }else if(letter.equals("__")){
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
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
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
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

    private ArrayList<AbbreviatedContact> getSelectedContactsList(Cursor cursor){
        ArrayList<AbbreviatedContact> contactsList = new ArrayList();
        AbbreviatedContact oneContact;
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
    private AbbreviatedContact getOneContactFromCursor(Cursor cursor){
        int id = cursor.getInt(0);
        String lastName = cursor.getString(1);
        String firstName = cursor.getString(2);
        String secondNameInitialLetter = cursor.getString(3);
        String patronymicInitialLetter = cursor.getString(4);
        String contactName =lastName +" "+ firstName +" "+ secondNameInitialLetter +" "+ patronymicInitialLetter;
        AbbreviatedContact contact = new AbbreviatedContact(id, contactName);
        return contact;
    }


    public void updateContactArrayAdapter(){

        Log.d("myLog","Адаптер обнвлен!");
        contactAdapter.notifyDataSetChanged();
    }

    public interface ContactListClickListener {
        public void onContactItemClick(int id);
    }

}
