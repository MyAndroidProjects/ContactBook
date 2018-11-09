package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.study.riseof.contactBookAndWeather.contactBook.ui.dialogFragment.ContactDeleteDialog;
import com.study.riseof.contactBookAndWeather.contactBook.ui.activity.EditContactActivity;
import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.database.ContactBaseManager;

import butterknife.BindView;
import butterknife.OnClick;

public class ButtonPanelFragment extends BaseFragment {

    @Nullable
    @BindView(R.id.button_edit)
    Button buttonEdit;
    @Nullable
    @BindView(R.id.button_delete)
    Button buttonDelete;
    @Nullable
    @BindView(R.id.button_add)
    Button buttonAdd;
    @Nullable
    @BindView(R.id.button_call)
    Button buttonCall;

    private ContactDeleteDialog contactDeleteDialog;
    private ContactBaseManager contactBaseManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_button_panel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        contactBaseManager = new ContactBaseManager(getContext());
        if (getArguments() != null) {
            Bundle args = getArguments();
            selectedContactId = args.getInt("selectedContactId", EMPTY_INDEX);
            selectedLetter = args.getString("selectedLetter", EMPTY_STRING);
        } else {
            selectedContactId = EMPTY_INDEX;
            selectedLetter = EMPTY_STRING;
        }

        return view;
    }

    @OnClick(R.id.button_edit)
    public void onClickEditContact() {
        if (selectedContactId == EMPTY_INDEX) {
            Toast.makeText(getContext(), "No selected contact", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getContext(), EditContactActivity.class);
            intent.putExtra("selectedContactId", selectedContactId);
            intent.putExtra("selectedLetter", selectedLetter);
            startActivity(intent);
        }
    }

    @OnClick(R.id.button_delete)
    public void onClickDeleteContact() {
        if (selectedContactId == EMPTY_INDEX) {
            Toast.makeText(getContext(), "No selected contact", Toast.LENGTH_SHORT).show();
        } else {
            contactDeleteDialog = new ContactDeleteDialog();
            Bundle args = new Bundle();
            args.putInt("selectedContactId", selectedContactId);
            args.putString("selectedLetter", selectedLetter);
            contactDeleteDialog.setArguments(args);
            contactDeleteDialog.show(getFragmentManager(), "contactDeleteDialog");
        }
    }

    @OnClick(R.id.button_add)
    public void onClickAddContact() {
        Intent intent = new Intent(getContext(), EditContactActivity.class);
        intent.putExtra("selectedLetter", selectedLetter);
        startActivity(intent);
    }

    @OnClick(R.id.button_call)
    public void onClickCallContact() {
        if (selectedContactId == EMPTY_INDEX) {
            Toast.makeText(getContext(), "No phone number", Toast.LENGTH_SHORT).show();
        } else {
            String phoneNumber = contactBaseManager.getMobileNumberById(selectedContactId);
            if (phoneNumber.equals("+79139533750")) {
                String toDial = "tel:" + phoneNumber;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toDial)));
            } else {
                Toast.makeText(getContext(), "Phone number is not correct", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setSelectedContactId(int id) {
        this.selectedContactId = id;
    }

    public void setSelectedLetter(String selectedLetter) {
        this.selectedLetter = selectedLetter;
    }
}
