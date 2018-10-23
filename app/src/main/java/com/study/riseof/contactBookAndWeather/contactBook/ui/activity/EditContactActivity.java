package com.study.riseof.contactBookAndWeather.contactBook.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.database.ContactBaseManager;
import com.study.riseof.contactBookAndWeather.contactBook.model.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditContactActivity extends AppCompatActivity {
    private final String EMPTY_STRING="";
    private final String POINT=".";
    private final int INITIAL_LETTER_INDEX = 0;
    private final int EMPTY_INDEX = -1;

    ContactBaseManager contactBaseManager;
    private int selectedContactId = EMPTY_INDEX;
    private String selectedLetter = EMPTY_STRING;

    @BindView(R.id.edit_text_first_name) TextView firstNameText;
    @BindView(R.id.edit_text_second_name) TextView secondNameText;
    @BindView(R.id.edit_text_patronymic) TextView patronymicText;
    @BindView(R.id.edit_text_last_name) TextView lastNameText;
    @BindView(R.id.edit_text_mobile_phone) TextView mobilePhoneText;
    @BindView(R.id.edit_text_home_phone) TextView homePhoneText;
    @BindView(R.id.edit_text_personal_website) TextView personalWebsiteText;
    @BindView(R.id.edit_text_e_mail) TextView eMailText;
    @BindView(R.id.edit_text_flat) TextView flatText;
    @BindView(R.id.edit_text_house) TextView houseText;
    @BindView(R.id.edit_text_street) TextView streetText;
    @BindView(R.id.edit_text_city) TextView cityText;
    @BindView(R.id.edit_text_state) TextView stateText;
    @BindView(R.id.edit_text_country) TextView countryText;
    @BindView(R.id.edit_text_post_code) TextView postCodeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        ButterKnife.bind(this);
        contactBaseManager = new ContactBaseManager(this);
        selectedContactId = getIntent().getIntExtra("selectedContactId",EMPTY_INDEX);
        if(selectedContactId == EMPTY_INDEX){
            setEmptyStringsToText();
        } else {
            selectContactInfoById(selectedContactId);
        }
        if(getIntent().getStringExtra("selectedLetter") == null){
            selectedLetter = EMPTY_STRING;
        } else {
            selectedLetter = getIntent().getStringExtra("selectedLetter");
        }
    }

    public void selectContactInfoById(int selectedContactId) {
        setContactEditText(contactBaseManager.getContactById(selectedContactId));
    }

    private void setContactEditText(Contact contact){
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

    public void setEmptyStringsToText(){
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

    @OnClick(R.id.edit_button_clear)
    public void onClickButtonClear() {
        setEmptyStringsToText();
    }
    @OnClick(R.id.edit_button_close)
    public void onClickButtonClose() {
        Intent intent = new Intent(this, ContactsMainActivity.class);
        intent.putExtra("selectedContactId", selectedContactId);
        intent.putExtra("selectedLetter", selectedLetter);
        startActivity(intent);
    }

    @OnClick(R.id.edit_button_save_contact)
    public void onClickButtonSave() {
        saveContactToContactBase(selectedContactId);
    }

    @OnClick(R.id.edit_button_ok)
    public void onClickButtonOk() {
        saveContactToContactBase(selectedContactId);
        Intent intent = new Intent(this, ContactsMainActivity.class);
        intent.putExtra("selectedContactId", selectedContactId);
        intent.putExtra("selectedLetter", selectedLetter);
        startActivity(intent);
    }

    private void saveContactToContactBase(int selectedContactId){
        Contact contact = getContactFromText();
        selectedLetter = getFirstLetter(contact.getLastName());
        if(selectedContactId == EMPTY_INDEX){
            this.selectedContactId = contactBaseManager.addNewContactToBase(contact);
        } else {
            contactBaseManager.updateContactInBase(selectedContactId, contact);
        }
    }

    private Contact getContactFromText(){
        Contact contact = new Contact(
                firstNameText.getText().toString(),
                getInitial(firstNameText.getText().toString()),
                secondNameText.getText().toString(),
                getInitial(secondNameText.getText().toString()),
                patronymicText.getText().toString(),
                getInitial(patronymicText.getText().toString()),
                lastNameText.getText().toString(),
                getInitial(lastNameText.getText().toString()),
                mobilePhoneText.getText().toString(),
                homePhoneText.getText().toString(),
                personalWebsiteText.getText().toString(),
                eMailText.getText().toString(),
                flatText.getText().toString(),
                houseText.getText().toString(),
                streetText.getText().toString(),
                cityText.getText().toString(),
                stateText.getText().toString(),
                countryText.getText().toString(),
                postCodeText.getText().toString()
        );
        return contact;
    }

    private String getFirstLetter(String word){
        String firstLetter;
        if(word.equals("")){
            firstLetter = EMPTY_STRING;
        }else {
            firstLetter = String.valueOf(word.charAt(INITIAL_LETTER_INDEX));;
        }
        return firstLetter;
    }

    private String getInitial(String word){
        String initial;
        if(word.equals("")){
            initial = EMPTY_STRING;
        }else {
            initial = getFirstLetter(word) + POINT;;
        }
        return initial;
    }
}
