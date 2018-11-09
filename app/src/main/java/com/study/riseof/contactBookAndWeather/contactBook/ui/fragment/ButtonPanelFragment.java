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

import butterknife.BindView;
import butterknife.OnClick;

public class ButtonPanelFragment extends BaseFragment {
    private final String TEL = "tel:";

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


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_button_panel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @OnClick(R.id.button_edit)
    public void onClickEditContact() {
        if (selectedContactId == EMPTY_INDEX) {
            Toast.makeText(getContext(), getString(R.string.no_selected_contact), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(), getString(R.string.no_selected_contact), Toast.LENGTH_SHORT).show();
        } else {
            // TODO перенести вызов диалога в activity, а здесь сделать событие о нажатии delete button и подписать на это activity
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
            Toast.makeText(getContext(), getString(R.string.no_phone_number), Toast.LENGTH_SHORT).show();
        } else {
            String phoneNumber = contactBaseManager.getMobileNumberById(selectedContactId);
            if (phoneNumber.equals(PHONE_TO_CALL)) {
                String toDial = TEL + phoneNumber;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toDial)));
            } else {
                Toast.makeText(getContext(), getString(R.string.phone_number_is_not_correct), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
