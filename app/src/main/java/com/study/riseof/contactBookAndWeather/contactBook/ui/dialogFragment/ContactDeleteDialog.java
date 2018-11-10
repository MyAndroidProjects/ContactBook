package com.study.riseof.contactBookAndWeather.contactBook.ui.dialogFragment;

import android.content.Context;
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
    private final int EMPTY_INDEX = -1;

    private Unbinder unbinder;
    private int selectedContactId = EMPTY_INDEX;
    private Context context;
    private DialogClickButtonPositiveListener dialogClickButtonPositiveListener;

    @BindView(R.id.dialog_delete_text_message)
    TextView dialogTextMessage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_contact_delete, container);
        unbinder = ButterKnife.bind(this, view);
        getArgumentsFromBundle();
        setDialogTextFields();
        return view;
    }

    private void setDialogTextFields() {
        context = getContext();
        if (context != null) {
            getDialog().setTitle(context.getString(R.string.dialog_title_text));
            ContactBaseManager contactBaseManager = new ContactBaseManager(context);
            AbbreviatedContact abbreviatedContact = contactBaseManager.getAbbrContactById(selectedContactId);
            String messageName = String.format(context.getString(R.string.dialog_message), abbreviatedContact.getContactName());
            dialogTextMessage.setText(messageName);
        }
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            selectedContactId = getArguments().getInt("selectedContactId", EMPTY_INDEX);
        } else {
            selectedContactId = EMPTY_INDEX;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.dialog_delete_button_positive)
    public void onClickButtonPositive() {
        ContactBaseManager contactBaseManager = new ContactBaseManager(context);
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
