package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.Unbinder;

public class BaseFragment extends Fragment {
    protected Unbinder unbinder;
    protected final int EMPTY_INDEX = -1;
    protected final String EMPTY_STRING = "";
    protected View view;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
