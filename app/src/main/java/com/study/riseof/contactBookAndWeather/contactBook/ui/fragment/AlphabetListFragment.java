package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.contactBook.model.AlphabetList;
import com.study.riseof.contactBookAndWeather.contactBook.ui.adapter.AlphabetRecyclerViewAdapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AlphabetListFragment extends BaseFragment {
    private static List<String> buttonName;

    static {
        buttonName = new ArrayList<>();
        buttonName.add("All");
        buttonName.add("__");
        int length = AlphabetList.alphabet.size();
        String letter;
        for (int i = 0; i < length; i++) {
            letter = AlphabetList.alphabet.get(i).toString();
            buttonName.add(letter);
        }
    }

  //  private Unbinder unbinder;

    @BindView(R.id.recycler_view_alphabet)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
  //  private View view;

    private int fragmentWidth;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_alphabet_list;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  view = inflater.inflate(R.layout.fragment_alphabet_list, container, false);
      //  unbinder = ButterKnife.bind(this, view);
        super.onCreateView(inflater, container, savedInstanceState);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AlphabetRecyclerViewAdapter adapter = new AlphabetRecyclerViewAdapter(getContext(), buttonName);
        recyclerView.setAdapter(adapter);

        view.post(new Runnable() {
            @Override
            public void run() {
                fragmentWidth = view.getWidth();
                ViewGroup.LayoutParams lp = recyclerView.getLayoutParams();
                lp.height = fragmentWidth; //public LayoutParams(int width, int height)
            }
        });
        return view;
    }

 /*   @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    */
}
