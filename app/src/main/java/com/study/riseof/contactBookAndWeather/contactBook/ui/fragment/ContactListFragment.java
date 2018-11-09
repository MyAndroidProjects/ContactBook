package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.model.AbbreviatedContact;
import com.study.riseof.contactBookAndWeather.contactBook.ui.adapter.ContactListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnItemClick;

public class ContactListFragment extends BaseFragment {
    private ArrayList<AbbreviatedContact> contacts = new ArrayList<>();
    private ContactListAdapter contactAdapter;
    private ContactListClickListener contactClickListener;

    @BindView(R.id.contact_list_view)
    ListView contactListView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_list;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContactArrayAdapter();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!selectedLetter.equals(EMPTY_STRING)) {
            showContactsByFirstLetter(selectedLetter);
        }
    }

    @OnItemClick(R.id.contact_list_view)
    public void OnItemSelected(int position) {
        AbbreviatedContact abbreviatedContact = contactAdapter.getItem(position);
        if (abbreviatedContact != null && contactClickListener != null) {
            contactClickListener.onContactItemClick(abbreviatedContact.getItemId());
        }
    }

    public void showContactsByFirstLetter(String letter) {
        this.selectedLetter = letter;
        ArrayList<AbbreviatedContact> selectedContacts = contactBaseManager.getAbbrContactListByFirstLetter(letter);
        setContacts(selectedContacts);
        setContactArrayAdapter();
    }

    private void setContacts(ArrayList<AbbreviatedContact> contacts) {
        this.contacts = contacts;
    }

    private void setContactArrayAdapter() {
        if (getContext() != null) {
            contactAdapter = new ContactListAdapter(getContext(), R.layout.item_list_view_contact, contacts);
        }
        contactListView.setAdapter(contactAdapter);
    }

    public void setContactClickListener(ContactListClickListener contactClickListener) {
        this.contactClickListener = contactClickListener;
    }

    public interface ContactListClickListener {
        void onContactItemClick(int id);
    }
}