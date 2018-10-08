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
    private ContactBaseManager contactBaseManager;

    @BindView(R.id.contact_list_view)
    ListView contactListView;

    ArrayList<String[]> contactsWithId = new ArrayList();
    ArrayList<AbbreviatedContact> contacts = new ArrayList();

    ContactListAdapter contactAdapter;
    View view;
    ContactListClickListener contactClickListener;

    private String selectedLetter = EMPTY_STRING;

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
            throw new ClassCastException(context.toString() + " must implement ContactListClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        contactBaseManager = new ContactBaseManager(getContext());
        Bundle args = getArguments();
        selectedLetter = args.getString("selectedLetter", EMPTY_STRING);

        setContactArrayAdapter();
        // как лучше ставить обработчик, ниже два варианта:
        AdapterView.OnItemClickListener abbreviatedContactListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                AbbreviatedContact abbreviatedContact = (AbbreviatedContact)parent.getItemAtPosition(position);
             //   Log.d("myLog", "click name "+abbreviatedContact.getContactName()+ " position = " + position + ", id = " + id+" abbreviatedContact.getItemId()= "+abbreviatedContact.getItemId());
              contactClickListener.onContactItemClick(abbreviatedContact.getItemId());
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!selectedLetter.equals(EMPTY_STRING)){
            showContactsByFirstLetter(selectedLetter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showContactsByFirstLetter(String letter){
        this.selectedLetter=letter;
        ArrayList<AbbreviatedContact> selectedContacts = contactBaseManager.getAbbrContactListByFirstLetter(letter);
        setContacts(selectedContacts);
        setContactArrayAdapter();
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




    public void updateContactArrayAdapter(){

        Log.d("myLog","Адаптер обнвлен!");
        contactAdapter.notifyDataSetChanged();
    }

    public interface ContactListClickListener {
        public void onContactItemClick(int id);
    }

}
