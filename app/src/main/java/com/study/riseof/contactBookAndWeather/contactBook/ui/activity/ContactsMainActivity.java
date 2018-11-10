package com.study.riseof.contactBookAndWeather.contactBook.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.database.ContactsBaseSQLiteHelper;
import com.study.riseof.contactBookAndWeather.contactBook.ui.fragment.AlphabetListFragment;
import com.study.riseof.contactBookAndWeather.contactBook.ui.fragment.ButtonPanelFragment;
import com.study.riseof.contactBookAndWeather.contactBook.ui.fragment.ContactInfoFragment;
import com.study.riseof.contactBookAndWeather.contactBook.ui.fragment.ContactListFragment;
import com.study.riseof.contactBookAndWeather.contactBook.ui.view.ResizeView;

import butterknife.BindView;

public class ContactsMainActivity extends BaseContactActivity
        implements AlphabetListFragment.LetterClick,
        ContactListFragment.ContactListClickListener,
        ButtonPanelFragment.DeleteContactListener,
        ResizeView.SeekBarProgressListener {

    private ContactListFragment contactListFragment;
    private ContactInfoFragment contactInfoFragment;
    private ButtonPanelFragment buttonPanelFragment;
    private AlphabetListFragment alphabetListFragment;


    @BindView(R.id.frame_contact_list)
    FrameLayout contactListFrame;
    @BindView(R.id.frame_contact_info)
    FrameLayout contactInfoFrame;
    @BindView(R.id.contact_toolbar)
    Toolbar toolbar;
    @BindView(R.id.resize_view)
    ResizeView resizeView;

    @Override
    int getActivityLayoutId() {
        return R.layout.activity_contacts_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
        addFragments();
        setListeners();
    }

     private void setActionBar(){
         setSupportActionBar(toolbar);
         if (getSupportActionBar() != null) {
             getSupportActionBar().setHomeButtonEnabled(true);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
             getSupportActionBar().setDisplayShowTitleEnabled(false);
         }
     }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (selectedContactId != EMPTY_INDEX) {
            onContactItemClick(selectedContactId);
        }
        if (!selectedLetter.equals(EMPTY_STRING)) {
            onLetterClick(selectedLetter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_my_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_contacts_add_contact:
                startEditContactActivity();
                return true;
            case R.id.menu_item_contacts_quit:
                Intent minimizeApp = new Intent(Intent.ACTION_MAIN);
                minimizeApp.addCategory(Intent.CATEGORY_HOME);
                minimizeApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(minimizeApp);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFragments() {
        alphabetListFragment = new AlphabetListFragment();
        contactListFragment = new ContactListFragment();
        contactInfoFragment = new ContactInfoFragment();
        buttonPanelFragment = new ButtonPanelFragment();

        Bundle buttonPanelArgs = new Bundle();
        buttonPanelArgs.putInt("selectedContactId", selectedContactId);
        buttonPanelArgs.putString("selectedLetter", selectedLetter);
        buttonPanelFragment.setArguments(buttonPanelArgs);
        Bundle contactInfoArgs = new Bundle();
        contactInfoArgs.putInt("selectedContactId", selectedContactId);
        contactInfoFragment.setArguments(contactInfoArgs);
        Bundle contactListArgs = new Bundle();
        contactListArgs.putString("selectedLetter", selectedLetter);
        contactListFragment.setArguments(contactListArgs);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_alphabet_list, alphabetListFragment);
        fragmentTransaction.add(R.id.frame_contact_list, contactListFragment);
        fragmentTransaction.add(R.id.frame_contact_info, contactInfoFragment);
        fragmentTransaction.add(R.id.frame_button_panel, buttonPanelFragment);
        fragmentTransaction.commit();
    }

    private void setListeners(){
        alphabetListFragment.setLetterClickListener(this);
        contactListFragment.setContactClickListener(this);
        buttonPanelFragment.setDeleteContactListener(this);
        resizeView.setSeekBarListener(this);
    }

    private void startEditContactActivity() {
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("selectedContactId", selectedContactId);
        intent.putExtra("selectedLetter", selectedLetter);
        startActivity(intent);
    }


    private void deleteBase(){
        // На случай необходимости удалить базу
        ContactsBaseSQLiteHelper contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(this);
        this.deleteDatabase(ContactsBaseSQLiteHelper.DATABASE_NAME);
    }

    @Override
    public void changeSeekBarProgress(int progress) {
        LinearLayout.LayoutParams paramsContactListFrame = (LinearLayout.LayoutParams) contactListFrame.getLayoutParams();
        LinearLayout.LayoutParams paramsContactInfoFrame = (LinearLayout.LayoutParams) contactInfoFrame.getLayoutParams();
        paramsContactListFrame.weight = (getResources().getInteger(R.integer.seek_bar_maximum) - progress);
        paramsContactInfoFrame.weight = progress;
        contactListFrame.setLayoutParams(paramsContactListFrame);
        contactInfoFrame.setLayoutParams(paramsContactInfoFrame);
    }

    @Override
    public void onContactItemClick(int id) {
        selectedContactId = id;
        contactInfoFragment.setContactInfoById(selectedContactId);
        buttonPanelFragment.setSelectedContactId(selectedContactId);
    }

    @Override
    public void onLetterClick(String letter) {
        selectedLetter = letter;
        contactListFragment.showContactsByFirstLetter(selectedLetter);
        buttonPanelFragment.setSelectedLetter(letter);
    }


    @Override
    public void contactWasDeleted() {
        contactListFragment.showContactsByFirstLetter(selectedLetter);
        contactInfoFragment.setEmptyStringsToText();
        selectedContactId = EMPTY_INDEX;
        buttonPanelFragment.setSelectedContactId(selectedContactId);
    }
}
