package com.study.riseof.contactbook;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AlphabetRecyclerViewAdapter extends RecyclerView.Adapter<AlphabetRecyclerViewAdapter.Holder> {

    private LayoutInflater inflater;
    private List<String> letters;
    private AdapterLetterClickListener adapterLetterClickListener;



    public AlphabetRecyclerViewAdapter(Context context, List<String> letters) {
        this.letters=letters;
        this.inflater = LayoutInflater.from(context);

     try {
            adapterLetterClickListener = (AdapterLetterClickListener) context;
        } catch (ClassCastException e) {
            Log.d("mylog",context.toString() + " must implement onSomeEventListener");
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recycler_view_alphabet_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView clickedLetter = ButterKnife.findById(view, R.id.letter);
                adapterLetterClickListener.adapterLetterClick(clickedLetter.getText().toString());
             /*   TextView clickedLetter = view.findViewById(R.id.letter);
                adapterLetterClickListener.adapterLetterClick(clickedLetter.getText().toString());*/
            }
        });

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int i) {
        String curLetter = letters.get(i);
        viewHolder.letterItem.setText(curLetter);
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.letter)
        TextView letterItem;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    public interface AdapterLetterClickListener {
        public void adapterLetterClick(String letter);
    }

}
