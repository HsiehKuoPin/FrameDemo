package com.benjamin.widget.wave;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.benjamin.R;

import java.util.ArrayList;
import java.util.List;

public class WaveLoadingView extends LinearLayout {
    private WaveHelper mWaveHelper;
    private WaveView waveView;
    private int borderColor = Color.parseColor("#44dabd8a");
    private int behindWaveColor = Color.parseColor("#44dab26a");
    private int frontWaveColor = Color.parseColor("#FFdab26a");
    private int borderWidth = 5;
    public WaveLoadingView(Context context) {
        super(context);
        init();
    }

    public WaveLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.widget_wave_loading,this,true);
        waveView = (WaveView) findViewById(R.id.wave);
        waveView.setShapeType(WaveView.ShapeType.CIRCLE);
        waveView.setBorder(borderWidth,borderColor);
        waveView.setWaveColor(behindWaveColor, frontWaveColor);

        mWaveHelper = new WaveHelper(waveView);
    }

    public void start(){
        setVisibility(VISIBLE);
        mWaveHelper.start();
    }

    public void cancel(){
        mWaveHelper.cancel();
        setVisibility(GONE);
    }


    private class WaveHelper {
        private WaveView mWaveView;

        private AnimatorSet mAnimatorSet;

        public WaveHelper(WaveView waveView) {
            mWaveView = waveView;
            initAnimation();
        }

        public void start() {
            mWaveView.setShowWave(true);
            if (mAnimatorSet != null) {
                mAnimatorSet.start();
            }
        }

        private void initAnimation() {
            List<Animator> animators = new ArrayList<>();

            // horizontal animation.
            // wave waves infinitely.
            ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                    mWaveView, "waveShiftRatio", 0f, 1f);
            waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
            waveShiftAnim.setDuration(1000);
            waveShiftAnim.setInterpolator(new LinearInterpolator());
            animators.add(waveShiftAnim);

            // vertical animation.
            // water level increases from 0 to center of WaveView
            ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                    mWaveView, "waterLevelRatio", 0.4f, 0.8f);
            waterLevelAnim.setDuration(5000);
            waterLevelAnim.setInterpolator(new DecelerateInterpolator());
            animators.add(waterLevelAnim);

            // amplitude animation.
            // wave grows big then grows small, repeatedly
            ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                    mWaveView, "amplitudeRatio", 0.0001f, 0.05f);
            amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
            amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
            amplitudeAnim.setDuration(1000);
            amplitudeAnim.setInterpolator(new LinearInterpolator());
            animators.add(amplitudeAnim);

            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(animators);
        }

        public void cancel() {
            if (mAnimatorSet != null) {
//            mAnimatorSet.cancel();
                mAnimatorSet.end();
            }
        }
    }
}