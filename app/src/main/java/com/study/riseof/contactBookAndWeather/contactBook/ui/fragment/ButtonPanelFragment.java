package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ButtonPanelFragment extends Fragment {

    private Unbinder unbinder;
    private final int EMPTY_INDEX = -1;
    private final String EMPTY_STRING = "";

    private int selectedContactId = EMPTY_INDEX;
    private String selectedLetter = EMPTY_STRING;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button_panel, container, false);
        unbinder = ButterKnife.bind(this, view);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_edit)
    public void onClickEditContact() {
        //  Log.d("myLog", "onClickEditContact id "+selectedContactId+" letter "+selectedLetter);
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
        selectedContactId = id;
    }

    public void setSelectedLetter(String selectedLetter) {
        this.selectedLetter = selectedLetter;
    }
}
