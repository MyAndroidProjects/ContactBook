package com.study.riseof.contactbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;

public class ContactListAdapter extends ArrayAdapter<AbbreviatedContact> {

    private LayoutInflater inflater;
    private int layout;
    private List<AbbreviatedContact> contacts;

    public ContactListAdapter(@NonNull Context context, int layout, @NonNull List<AbbreviatedContact> contacts) {
        super(context, layout, contacts);
        this.contacts = contacts;
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView itemText = ButterKnife.findById(view, R.id.contact_list_item_text);
        AbbreviatedContact contact = contacts.get(position);
        itemText.setText(contact.getContactName());
        return view;
    }
}
