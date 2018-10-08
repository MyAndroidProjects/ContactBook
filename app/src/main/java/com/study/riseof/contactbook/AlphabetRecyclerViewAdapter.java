package com.study.riseof.contactbook;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
    private AnimatorSet[] animatorSets;
    private AdapterLetterClickListener adapterLetterClickListener;

    private View currentView=null;
    private int selectedPosition=-1;

    public AlphabetRecyclerViewAdapter(Context context, List<String> letters) {
        this.letters=letters;
        this.inflater = LayoutInflater.from(context);
        int lettersSize = letters.size();
        animatorSets = new AnimatorSet[lettersSize];
        for (int i=0;i<lettersSize;i++){
            animatorSets[i] = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.animator_pressed_letter);
        }

        try {
            adapterLetterClickListener = (AdapterLetterClickListener) context;
        } catch (ClassCastException e) {
            Log.d("mylog",context.toString() + " must implement AdapterLetterClickListener");
            throw new ClassCastException(context.toString() + " must implement AdapterLetterClickListener");
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recycler_view_alphabet_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, final int position) {
        final String curLetter = letters.get(position);
        viewHolder.letterItem.setText(curLetter);
        // Log.d("myLog"," 1 position = " + position + " selectedPosition = "+selectedPosition);
        if(selectedPosition >= 0){
            animatorSets[selectedPosition].cancel();
            animatorSets[selectedPosition].end();
        }
        viewHolder.itemView.setScaleX(1f);
        viewHolder.itemView.setScaleY(1f);
        if(selectedPosition == position){
            //   Log.d("myLog"," 2 position = " + position + " selectedPosition = "+selectedPosition);
            animatorSets[selectedPosition].start();
        }
        viewHolder.letterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterLetterClickListener.adapterLetterClick(curLetter);
                //   Log.d("myLog"," 3 position = " + position + " selectedPosition = "+selectedPosition);
                if(selectedPosition >= 0 && animatorSets[selectedPosition].isRunning()){
                    //       Log.d("myLog"," 4 position = " + position + " selectedPosition = "+selectedPosition);
                    //   animatorSet.end();
                    animatorSets[selectedPosition].cancel();
                    animatorSets[selectedPosition].end();
                    // animatorSet.end(); не возвращает значение scale в исходное, поэтому добавил
                    currentView.setScaleX(1f);
                    currentView.setScaleY(1f);
                }
                selectedPosition = position;
                currentView = view;
                //     Log.d("myLog"," 5 position = " + position + " selectedPosition = "+selectedPosition);
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

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    public interface AdapterLetterClickListener {
        public void adapterLetterClick(String letter);
    }
}
