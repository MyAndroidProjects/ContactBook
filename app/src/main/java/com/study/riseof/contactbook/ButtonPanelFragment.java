package com.study.riseof.contactbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ButtonPanelFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @BindView(R.id.button_call)
    Button callButton;
    @Nullable
    @BindView(R.id.button_sms)
    Button smsButton;
    @Nullable
    @BindView(R.id.button_edit)
    Button editButton;
    @Nullable
    @BindView(R.id.button_expand)
    Button expandButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_button_panel, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
