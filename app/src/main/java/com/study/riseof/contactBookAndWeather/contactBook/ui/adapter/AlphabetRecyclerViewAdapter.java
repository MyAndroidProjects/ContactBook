package com.study.riseof.contactBookAndWeather.contactBook.ui.adapter;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.riseof.contactBookAndWeather.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlphabetRecyclerViewAdapter extends RecyclerView.Adapter<AlphabetRecyclerViewAdapter.Holder> {

    private final LayoutInflater inflater;
    private final List<String> letters;
    private final AnimatorSet[] animatorSets;
    private AdapterLetterClickListener adapterLetterClickListener;
    private View currentView = null;
    private int selectedPosition = -1;

    public AlphabetRecyclerViewAdapter(Context context, List<String> letters) {
        this.letters = letters;
        this.inflater = LayoutInflater.from(context);
        int lettersSize = letters.size();
        animatorSets = new AnimatorSet[lettersSize];
        for (int i = 0; i < lettersSize; i++) {
            animatorSets[i] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.animator_pressed_letter);
        }

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_recycler_view_alphabet, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
        final int pos = position;
        final String curLetter = letters.get(position);
        viewHolder.letterItem.setText(curLetter);
        if (selectedPosition >= 0) {
            animatorSets[selectedPosition].cancel();
            animatorSets[selectedPosition].end();
        }
        viewHolder.itemView.setScaleX(1f);
        viewHolder.itemView.setScaleY(1f);
        if (selectedPosition == position) {
            animatorSets[selectedPosition].start();
        }
        viewHolder.letterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterLetterClickListener.adapterLetterClick(curLetter);
                if (selectedPosition >= 0 && animatorSets[selectedPosition].isRunning()) {
                    animatorSets[selectedPosition].cancel();
                    animatorSets[selectedPosition].end();
                    // animatorSet.end(); не возвращает значение scale в исходное, поэтому добавил
                    currentView.setScaleX(1f);
                    currentView.setScaleY(1f);
                }
                selectedPosition = pos;
                currentView = view;
                animatorSets[selectedPosition].setTarget(currentView);
                animatorSets[selectedPosition].start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.letter)
        TextView letterItem;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setAdapterLetterClickListener(AdapterLetterClickListener adapterLetterClickListener) {
        this.adapterLetterClickListener = adapterLetterClickListener;
    }
    public interface AdapterLetterClickListener {
        void adapterLetterClick(String letter);
    }
}
