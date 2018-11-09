package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.study.riseof.contactBookAndWeather.R;

import butterknife.BindView;

public class SeekBarFragment extends BaseFragment {
    private final float PADDING_PART_OF_FRAGMENT = 0.1f;

    private SeekBarProgressListener seekBarListener;
    private int fragmentHeight;


    @BindView(R.id.window_height_seek_bar)
    SeekBar windowHeightBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seek_bar;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            seekBarListener = (SeekBarProgressListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " must implement SeekBarProgressListener");
        }
        super.onCreateView(inflater, container, savedInstanceState);
        windowHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //   seekBarListener.changeSeekBarProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarListener.changeSeekBarProgress(seekBar.getProgress());
            }
        });
        view.post(new Runnable() {
            @Override
            public void run() {
                fragmentHeight = view.getHeight();
                ViewGroup.LayoutParams lp = windowHeightBar.getLayoutParams();
                lp.width = fragmentHeight; //public LayoutParams(int width, int height)
                windowHeightBar.setPadding((int) (fragmentHeight * PADDING_PART_OF_FRAGMENT), 0, (int) (fragmentHeight * PADDING_PART_OF_FRAGMENT), 0);
                seekBarListener.changeSeekBarProgress(windowHeightBar.getProgress());
            }
        });
        return view;
    }

    public interface SeekBarProgressListener {
        void changeSeekBarProgress(int progress);
    }
}
