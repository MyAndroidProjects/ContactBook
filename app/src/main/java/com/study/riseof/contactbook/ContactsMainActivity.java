package com.study.riseof.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsMainActivity extends AppCompatActivity
        implements SeekBarFragment.SeekBarProgressListener,
        AlphabetRecyclerViewAdapter.AdapterLetterClickListener,
        ContactListFragment.ContactListClickListener,
        ContactDeleteDialog.DialogClickButtonPositiveListener{

    private final String EMPTY_STRING = "";
    private final int EMPTY_INDEX = -1;

    ContactListFragment contactListFragment;
    ContactInfoFragment contactInfoFragment;
    ButtonPanelFragment buttonPanelFragment;
    AlphabetListFragment alphabetListFragment;
    private int selectedContactId = EMPTY_INDEX;
    private String selectedLetter = EMPTY_STRING;
    private int maxSeekBar;

    @BindView(R.id.frame_contact_list)
    FrameLayout contactListFrame;
    @BindView(R.id.frame_contact_info)
    FrameLayout contactInfoFrame;
    @BindView(R.id.contact_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_main);
        //  ContactsBaseSQLiteHelper contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(this);
        //this.deleteDatabase(ContactsBaseSQLiteHelper.DATABASE_NAME);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        maxSeekBar = getResources().getInteger(R.integer.seek_bar_maximum);
        selectedContactId = getIntent().getIntExtra("selectedContactId",EMPTY_INDEX);
        if(getIntent().getStringExtra("selectedLetter") == null){
            selectedLetter = EMPTY_STRING;
        } else {
            selectedLetter = getIntent().getStringExtra("selectedLetter");
        }
        addFragments();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   ContactsBaseSQLiteHelper contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(this);
        //  this.deleteDatabase(ContactsBaseSQLiteHelper.DATABASE_NAME);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedContactId", selectedContactId);
        outState.putString("selectedLetter", selectedLetter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedContactId = savedInstanceState.getInt("selectedContactId", EMPTY_INDEX);
        selectedLetter = savedInstanceState.getString("selectedLetter", EMPTY_STRING);
        if(selectedContactId != EMPTY_INDEX){
            onContactItemClick(selectedContactId);
        }
        if(!selectedLetter.equals(EMPTY_STRING)){
            adapterLetterClick(selectedLetter);
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
        switch(id){
            case R.id.menu_item_contacts_add_contact :
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

    private void addFragments(){
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

    @Override
    public void changeSeekBarProgress(int progress) {
        LinearLayout.LayoutParams paramsContactListFrame = (LinearLayout.LayoutParams)contactListFrame.getLayoutParams();
        LinearLayout.LayoutParams paramsContactInfoFrame = (LinearLayout.LayoutParams)contactInfoFrame.getLayoutParams();
        paramsContactListFrame.weight=(maxSeekBar-progress);
        paramsContactInfoFrame.weight=progress;
        contactListFrame.setLayoutParams(paramsContactListFrame);
        contactInfoFrame.setLayoutParams(paramsContactInfoFrame);
    }

    @Override
    public void adapterLetterClick(String letter) {
        selectedLetter = letter;
        contactListFragment.showContactsByFirstLetter(selectedLetter);
        buttonPanelFragment.setSelectedLetter(letter);
    }

    @Override
    public void onContactItemClick(int id) {
        selectedContactId = id;
        contactInfoFragment.setContactInfoById(selectedContactId);
        buttonPanelFragment.setSelectedContactId(selectedContactId);
    }

    private void startEditContactActivity(){
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("selectedContactId", selectedContactId);
        intent.putExtra("selectedLetter", selectedLetter);
        startActivity(intent);
    }

    @Override
    public void dialogClickButtonPositive() {
        contactListFragment.showContactsByFirstLetter(selectedLetter);
        contactInfoFragment.setEmptyStringsToText();
        selectedContactId = EMPTY_INDEX;
        buttonPanelFragment.setSelectedContactId(selectedContactId);
    }
}
