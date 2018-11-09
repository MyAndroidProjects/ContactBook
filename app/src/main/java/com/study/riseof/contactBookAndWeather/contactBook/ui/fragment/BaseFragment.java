package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.riseof.contactBookAndWeather.contactBook.database.ContactBaseManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected final int EMPTY_INDEX = -1;
    protected final String EMPTY_STRING = "";
    protected final String PHONE_TO_CALL = "+79139533750";


    protected Unbinder unbinder;
    protected View view;
    protected int selectedContactId = EMPTY_INDEX;
    protected String selectedLetter = EMPTY_STRING;
    protected ContactBaseManager contactBaseManager;
    protected abstract int getLayoutId();

    public void setSelectedContactId(int id) {
        this.selectedContactId = id;
    }

    public void setSelectedLetter(String selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        contactBaseManager = new ContactBaseManager(getContext());
        if (this.getArguments() != null) {
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
}
