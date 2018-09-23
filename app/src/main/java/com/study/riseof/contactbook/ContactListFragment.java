package com.study.riseof.contactbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactListFragment extends Fragment {
   // private final int MAX_WIDTH_FRAME_FRAGMENT=100;
    private Unbinder unbinder;

    @BindView(R.id.contact_list_view)
    ListView contactListView;

    ArrayList<String> contacts = new ArrayList();
    ArrayAdapter<String> contactAdapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        setContactArrayAdapter();


        //  windowHeightBar=(SeekBar)findViewById(R.id.windowHeightBar);

     /*   FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) windowHeightBar.getLayoutParams(); // получаем параметры

        params.width = screenHeight;
        params.height = screenHeight;// меняем высоту. Если уползёт выравнивание, то imageView.getLayoutParams().width = MyHeight;
        windowHeightBar.setLayoutParams(params); // меняем параметр*/

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setContacts(ArrayList<String> contacts) {
        Log.d("myLog","Контакты обновлены!");
        this.contacts = contacts;
    }

    public void setContactArrayAdapter(){
        contactAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, contacts);
        contactListView.setAdapter(contactAdapter);
        Log.d("myLog","Адаптер установлен!");
    }
}
