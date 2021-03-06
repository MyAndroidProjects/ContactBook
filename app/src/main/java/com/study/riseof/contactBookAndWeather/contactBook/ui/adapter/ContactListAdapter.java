package com.study.riseof.contactBookAndWeather.contactBook.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.model.AbbreviatedContact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListAdapter extends ArrayAdapter<AbbreviatedContact> {

    private final LayoutInflater inflater;
    private final int layout;
    private final List<AbbreviatedContact> contacts;

    public ContactListAdapter(@NonNull Context context, int layout, @NonNull List<AbbreviatedContact> contacts) {
        super(context, layout, contacts);
        this.contacts = contacts;
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ContactListAdaptersHolder contactListAdaptersHolder;
        View view = convertView;
        if (view == null || view.getTag() == null) {
            view = inflater.inflate(this.layout, parent, false);
            contactListAdaptersHolder = new ContactListAdaptersHolder(view);
            view.setTag(contactListAdaptersHolder);
        } else {
            contactListAdaptersHolder = (ContactListAdaptersHolder) view.getTag();
        }
        AbbreviatedContact contact = contacts.get(position);
        contactListAdaptersHolder.itemText.setText(contact.getContactName());
        return view;
    }

    static final class ContactListAdaptersHolder {
        @BindView(R.id.contact_list_item_text)
        TextView itemText;

        ContactListAdaptersHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
