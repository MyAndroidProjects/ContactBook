package com.study.riseof.contactBookAndWeather.contactBook.ui.dialogFragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.database.ContactBaseManager;
import com.study.riseof.contactBookAndWeather.contactBook.model.AbbreviatedContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactDeleteDialog extends DialogFragment {
    private final String EMPTY_STRING = "";
    private final int EMPTY_INDEX = -1;
    private final String TITLE_TEXT = "Caution!";
    private final String MESSAGE_TEXT = "Delete\n";

    private Unbinder unbinder;
    private int selectedContactId = EMPTY_INDEX;
    private String messageName = EMPTY_STRING;

    private DialogClickButtonPositiveListener dialogClickButtonPositiveListener;

    @BindView(R.id.dialog_delete_text_message)
    TextView dialogTextMessage;
    @BindView(R.id.dialog_delete_text_name)
    TextView dialogTextContactName;

     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_contact_delete, container);
        unbinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            selectedContactId = getArguments().getInt("selectedContactId", EMPTY_INDEX);
        } else {
            selectedContactId = EMPTY_INDEX;
        }
        ContactBaseManager contactBaseManager = new ContactBaseManager(getContext());
        AbbreviatedContact abbreviatedContact = contactBaseManager.getAbbrContactById(selectedContactId);
        getDialog().setTitle(TITLE_TEXT);
        getDialog().setCancelable(false); //не работает
        dialogTextMessage.setText(MESSAGE_TEXT);
        messageName = abbreviatedContact.getContactName() + " ?";
        dialogTextContactName.setText(messageName);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.dialog_delete_button_positive)
    public void onClickButtonPositive() {
        ContactBaseManager contactBaseManager = new ContactBaseManager(getContext());
        contactBaseManager.deleteContactFromBase(selectedContactId);
        dialogClickButtonPositiveListener.dialogClickButtonPositive();
        getDialog().dismiss();
    }

    @OnClick(R.id.dialog_delete_button_negative)
    public void onClickButtonNegative() {
        getDialog().dismiss();
    }

    public void setDialogClickButtonPositiveListener(DialogClickButtonPositiveListener dialogClickButtonPositiveListener) {
        this.dialogClickButtonPositiveListener = dialogClickButtonPositiveListener;
    }


    public interface DialogClickButtonPositiveListener {
        void dialogClickButtonPositive();
    }


}
