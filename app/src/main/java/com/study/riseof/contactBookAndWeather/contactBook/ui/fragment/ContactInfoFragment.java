package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.model.Contact;

import butterknife.BindView;

public class ContactInfoFragment extends BaseFragment {
    @BindView(R.id.text_first_name)
    TextView firstNameText;
    @BindView(R.id.text_second_name)
    TextView secondNameText;
    @BindView(R.id.text_patronymic)
    TextView patronymicText;
    @BindView(R.id.text_last_name)
    TextView lastNameText;
    @BindView(R.id.text_mobile_phone)
    TextView mobilePhoneText;
    @BindView(R.id.text_home_phone)
    TextView homePhoneText;
    @BindView(R.id.text_personal_website)
    TextView personalWebsiteText;
    @BindView(R.id.text_e_mail)
    TextView eMailText;
    @BindView(R.id.text_flat)
    TextView flatText;
    @BindView(R.id.text_house)
    TextView houseText;
    @BindView(R.id.text_street)
    TextView streetText;
    @BindView(R.id.text_city)
    TextView cityText;
    @BindView(R.id.text_state)
    TextView stateText;
    @BindView(R.id.text_country)
    TextView countryText;
    @BindView(R.id.text_post_code)
    TextView postCodeText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_info;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (selectedContactId != EMPTY_INDEX) {
            setContactInfoById(selectedContactId);
        }
    }

    public void setContactInfoById(int id) {
        setContactInfoText(contactBaseManager.getContactById(id));
    }

    private void setContactInfoText(Contact contact) {
        firstNameText.setText(contact.getFirstName());
        secondNameText.setText(contact.getSecondName());
        patronymicText.setText(contact.getPatronymic());
        lastNameText.setText(contact.getLastName());
        mobilePhoneText.setText(contact.getMobilePhone());
        homePhoneText.setText(contact.getHomePhone());
        personalWebsiteText.setText(contact.getPersonalWebsite());
        eMailText.setText(contact.getEMail());
        flatText.setText(contact.getFlat());
        houseText.setText(contact.getHouse());
        streetText.setText(contact.getStreet());
        cityText.setText(contact.getCity());
        stateText.setText(contact.getState());
        countryText.setText(contact.getCountry());
        postCodeText.setText(contact.getPostCode());
    }

    public void setEmptyStringsToText() {
        firstNameText.setText(EMPTY_STRING);
        secondNameText.setText(EMPTY_STRING);
        patronymicText.setText(EMPTY_STRING);
        lastNameText.setText(EMPTY_STRING);
        mobilePhoneText.setText(EMPTY_STRING);
        homePhoneText.setText(EMPTY_STRING);
        personalWebsiteText.setText(EMPTY_STRING);
        eMailText.setText(EMPTY_STRING);
        flatText.setText(EMPTY_STRING);
        houseText.setText(EMPTY_STRING);
        streetText.setText(EMPTY_STRING);
        cityText.setText(EMPTY_STRING);
        stateText.setText(EMPTY_STRING);
        countryText.setText(EMPTY_STRING);
        postCodeText.setText(EMPTY_STRING);
    }
}
