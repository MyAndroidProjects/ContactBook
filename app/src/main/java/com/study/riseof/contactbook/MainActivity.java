package com.study.riseof.contactbook;

import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SeekBarFragment.SeekBarProgressListener{

    @BindView(R.id.contact_list)
    FrameLayout contactListFrame;
    @BindView(R.id.contact_info)
    FrameLayout contactInfoFrame;

    Fragment contactListFragment;
    Fragment contactInfoFragment;
    Fragment buttonPanelFragment;

    private int maxSeekBar;
    private int buttonPanelWeight;

    public static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        maxSeekBar = getResources().getInteger(R.integer.seek_bar_maximum);
        buttonPanelWeight = getResources().getInteger(R.integer.fragment_button_panel_vertical_weight);

        addFragments();

        Log.d(TAG, "buttonPanelWeight = "+ buttonPanelWeight);
        Log.d(TAG, "maxSeekBar= " + maxSeekBar);
        Log.d(TAG, "ttttttttt = "+ getBaseContext().getResources().getInteger(R.integer.seek_bar_rotation));
   }

    private void addFragments(){
        contactListFragment = new ContactListFragment();
        contactInfoFragment = new ContactInfoFragment();
        buttonPanelFragment = new ButtonPanelFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contact_list,contactListFragment);
        fragmentTransaction.add(R.id.contact_info,contactInfoFragment);
        fragmentTransaction.add(R.id.button_panel,buttonPanelFragment);
        fragmentTransaction.commit();
    }

    private void replaceFragments(){
        contactListFragment = new ContactListFragment();
        contactInfoFragment = new ContactInfoFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contact_list,contactListFragment);
        fragmentTransaction.replace(R.id.contact_info,contactInfoFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void changeSeekBarProgress(int progress) {

        LinearLayout.LayoutParams paramsContactListFrame = (LinearLayout.LayoutParams)contactListFrame.getLayoutParams();
        LinearLayout.LayoutParams paramsContactInfoFrame = (LinearLayout.LayoutParams)contactInfoFrame.getLayoutParams();

        paramsContactListFrame.weight=(maxSeekBar-progress)+buttonPanelWeight;
        paramsContactInfoFrame.weight=progress;
        contactListFrame.setLayoutParams(paramsContactListFrame);
        contactInfoFrame.setLayoutParams(paramsContactInfoFrame);
    }
}
