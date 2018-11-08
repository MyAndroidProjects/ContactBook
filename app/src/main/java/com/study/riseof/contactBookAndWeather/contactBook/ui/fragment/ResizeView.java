package com.study.riseof.contactBookAndWeather.contactBook.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.study.riseof.contactBookAndWeather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ResizeView extends FrameLayout {

    private final float PADDING_PART_OF_FRAGMENT = 0.1f;

    private SeekBarProgressListener seekBarListener;
    private int fragmentHeight;


    @BindView(R.id.window_height_seek_bar)
    SeekBar windowHeightBar;

    public ResizeView(@NonNull Context context) {
        super(context);
        init();
    }

    public ResizeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    protected void init() {
        inflate(getContext(), R.layout.fragment_seek_bar, this);
        ButterKnife.bind(this);

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
        this.post(new Runnable() {
            @Override
            public void run() {
                fragmentHeight = getHeight();
                ViewGroup.LayoutParams lp = windowHeightBar.getLayoutParams();
                lp.width = fragmentHeight; //public LayoutParams(int width, int height)
                windowHeightBar.setPadding((int) (fragmentHeight * PADDING_PART_OF_FRAGMENT), 0, (int) (fragmentHeight * PADDING_PART_OF_FRAGMENT), 0);
                seekBarListener.changeSeekBarProgress(windowHeightBar.getProgress());
            }
        });
    }

    public void setSeekBarListener(SeekBarProgressListener seekBarListener) {
        this.seekBarListener = seekBarListener;
    }

    public interface SeekBarProgressListener {
        void changeSeekBarProgress(int progress);
    }
}