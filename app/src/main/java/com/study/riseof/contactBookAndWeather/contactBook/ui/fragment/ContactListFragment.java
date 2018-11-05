package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import android.widget.AdapterView;

import com.study.riseof.contactBookAndWeather.contactBook.ui.adapter.ContactListAdapter;
import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.database.ContactBaseManager;
import com.study.riseof.contactBookAndWeather.contactBook.model.AbbreviatedContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactListFragment extends BaseFragment {
  //  private final String EMPTY_STRING = "";

 //   private Unbinder unbinder;
    private ContactBaseManager contactBaseManager;
    private ArrayList<AbbreviatedContact> contacts = new ArrayList<>();
    private ContactListAdapter contactAdapter;
 //   private View view;
    private ContactListClickListener contactClickListener;
    private String selectedLetter = EMPTY_STRING;

    @BindView(R.id.contact_list_view)
    ListView contactListView;

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
            contactClickListener = (ContactListClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ContactListClickListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        contactBaseManager = new ContactBaseManager(getContext());
        if (getArguments() != null) {
            Bundle args = getArguments();
            selectedLetter = args.getString("selectedLetter", EMPTY_STRING);
        } else {
            selectedLetter = EMPTY_STRING;
        }
        setContactArrayAdapter();
        // ??? как лучше ставить обработчик, ниже два варианта:
        AdapterView.OnItemClickListener abbreviatedContactListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                AbbreviatedContact abbreviatedContact = (AbbreviatedContact) parent.getItemAtPosition(position);
                contactClickListener.onContactItemClick(abbreviatedContact.getItemId());
            }
        };
        contactListView.setOnItemClickListener(abbreviatedContactListener);
        // ??? и второй вариант:
        /*  contactListView.setOnItemClickListener(new OnItemClickListener() {
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
        if (!selectedLetter.equals(EMPTY_STRING)) {
            showContactsByFirstLetter(selectedLetter);
        }
    }
/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
*/
    public void showContactsByFirstLetter(String letter) {
        this.selectedLetter = letter;
        ArrayList<AbbreviatedContact> selectedContacts = contactBaseManager.getAbbrContactListByFirstLetter(letter);
        setContacts(selectedContacts);
        setContactArrayAdapter();
    }

    public void setContacts(ArrayList<AbbreviatedContact> contacts) {
        this.contacts = contacts;
    }

    public void setContactArrayAdapter() {
        if (getContext() != null) {
            contactAdapter = new ContactListAdapter(getContext(), R.layout.item_list_view_contact, contacts);
        }
        contactListView.setAdapter(contactAdapter);
    }

    public interface ContactListClickListener {
        void onContactItemClick(int id);
    }
}
