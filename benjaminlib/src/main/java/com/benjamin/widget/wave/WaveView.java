//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.benjamin.widget.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {
    private static final float DEFAULT_AMPLITUDE_RATIO = 0.05F;
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5F;
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0F;
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0F;
    public static final int DEFAULT_BEHIND_WAVE_COLOR = Color.parseColor("#28FFFFFF");
    public static final int DEFAULT_FRONT_WAVE_COLOR = Color.parseColor("#3CFFFFFF");
    public static final WaveView.ShapeType DEFAULT_WAVE_SHAPE;
    private boolean mShowWave;
    private BitmapShader mWaveShader;
    private Matrix mShaderMatrix;
    private Paint mViewPaint;
    private Paint mBorderPaint;
    private float mDefaultAmplitude;
    private float mDefaultWaterLevel;
    private float mDefaultWaveLength;
    private double mDefaultAngularFrequency;
    private float mAmplitudeRatio = 0.05F;
    private float mWaveLengthRatio = 1.0F;
    private float mWaterLevelRatio = 0.5F;
    private float mWaveShiftRatio = 0.0F;
    private int mBehindWaveColor;
    private int mFrontWaveColor;
    private WaveView.ShapeType mShapeType;

    public WaveView(Context context) {
        super(context);
        this.mBehindWaveColor = DEFAULT_BEHIND_WAVE_COLOR;
        this.mFrontWaveColor = DEFAULT_FRONT_WAVE_COLOR;
        this.mShapeType = DEFAULT_WAVE_SHAPE;
        this.init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mBehindWaveColor = DEFAULT_BEHIND_WAVE_COLOR;
        this.mFrontWaveColor = DEFAULT_FRONT_WAVE_COLOR;
        this.mShapeType = DEFAULT_WAVE_SHAPE;
        this.init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mBehindWaveColor = DEFAULT_BEHIND_WAVE_COLOR;
        this.mFrontWaveColor = DEFAULT_FRONT_WAVE_COLOR;
        this.mShapeType = DEFAULT_WAVE_SHAPE;
        this.init();
    }

    private void init() {
        this.mShaderMatrix = new Matrix();
        this.mViewPaint = new Paint();
        this.mViewPaint.setAntiAlias(true);
    }

    public float getWaveShiftRatio() {
        return this.mWaveShiftRatio;
    }

    public void setWaveShiftRatio(float waveShiftRatio) {
        if (this.mWaveShiftRatio != waveShiftRatio) {
            this.mWaveShiftRatio = waveShiftRatio;
            this.invalidate();
        }

    }

    public float getWaterLevelRatio() {
        return this.mWaterLevelRatio;
    }

    public void setWaterLevelRatio(float waterLevelRatio) {
        if (this.mWaterLevelRatio != waterLevelRatio) {
            this.mWaterLevelRatio = waterLevelRatio;
            this.invalidate();
        }

    }

    public float getAmplitudeRatio() {
        return this.mAmplitudeRatio;
    }

    public void setAmplitudeRatio(float amplitudeRatio) {
        if (this.mAmplitudeRatio != amplitudeRatio) {
            this.mAmplitudeRatio = amplitudeRatio;
            this.invalidate();
        }

    }

    public float getWaveLengthRatio() {
        return this.mWaveLengthRatio;
    }

    public void setWaveLengthRatio(float waveLengthRatio) {
        this.mWaveLengthRatio = waveLengthRatio;
    }

    public boolean isShowWave() {
        return this.mShowWave;
    }

    public void setShowWave(boolean showWave) {
        this.mShowWave = showWave;
    }

    public void setBorder(int width, int color) {
        if (this.mBorderPaint == null) {
            this.mBorderPaint = new Paint();
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setStyle(Style.STROKE);
        }

        this.mBorderPaint.setColor(color);
        this.mBorderPaint.setStrokeWidth((float)width);
        this.invalidate();
    }

    public void setWaveColor(int behindWaveColor, int frontWaveColor) {
        this.mBehindWaveColor = behindWaveColor;
        this.mFrontWaveColor = frontWaveColor;
        if (this.getWidth() > 0 && this.getHeight() > 0) {
            this.mWaveShader = null;
            this.createShader();
            this.invalidate();
        }

    }

    public void setShapeType(WaveView.ShapeType shapeType) {
        this.mShapeType = shapeType;
        this.invalidate();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.createShader();
    }

    private void createShader() {
        this.mDefaultAngularFrequency = 6.283185307179586D / (double)this.getWidth();
        this.mDefaultAmplitude = (float)this.getHeight() * 0.05F;
        this.mDefaultWaterLevel = (float)this.getHeight() * 0.5F;
        this.mDefaultWaveLength = (float)this.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint wavePaint = new Paint();
        wavePaint.setStrokeWidth(2.0F);
        wavePaint.setAntiAlias(true);
        int endX = this.getWidth() + 1;
        int endY = this.getHeight() + 1;
        float[] waveY = new float[endX];
        wavePaint.setColor(this.mBehindWaveColor);

        int wave2Shift;
        for(wave2Shift = 0; wave2Shift < endX; ++wave2Shift) {
            double wx = (double)wave2Shift * this.mDefaultAngularFrequency;
            float beginY = (float)((double)this.mDefaultWaterLevel + (double)this.mDefaultAmplitude * Math.sin(wx));
            canvas.drawLine((float)wave2Shift, beginY, (float)wave2Shift, (float)endY, wavePaint);
            waveY[wave2Shift] = beginY;
        }

        wavePaint.setColor(this.mFrontWaveColor);
        wave2Shift = (int)(this.mDefaultWaveLength / 4.0F);

        for(int beginX = 0; beginX < endX; ++beginX) {
            canvas.drawLine((float)beginX, waveY[(beginX + wave2Shift) % endX], (float)beginX, (float)endY, wavePaint);
        }

        this.mWaveShader = new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP);
        this.mViewPaint.setShader(this.mWaveShader);
    }

    protected void onDraw(Canvas canvas) {
        if (this.mShowWave && this.mWaveShader != null) {
            if (this.mViewPaint.getShader() == null) {
                this.mViewPaint.setShader(this.mWaveShader);
            }

            this.mShaderMatrix.setScale(this.mWaveLengthRatio / 1.0F, this.mAmplitudeRatio / 0.05F, 0.0F, this.mDefaultWaterLevel);
            this.mShaderMatrix.postTranslate(this.mWaveShiftRatio * (float)this.getWidth(), (0.5F - this.mWaterLevelRatio) * (float)this.getHeight());
            this.mWaveShader.setLocalMatrix(this.mShaderMatrix);
            float borderWidth = this.mBorderPaint == null ? 0.0F : this.mBorderPaint.getStrokeWidth();
            switch(this.mShapeType) {
            case CIRCLE:
                if (borderWidth > 0.0F) {
                    canvas.drawCircle((float)this.getWidth() / 2.0F, (float)this.getHeight() / 2.0F, ((float)this.getWidth() - borderWidth) / 2.0F - 1.0F, this.mBorderPaint);
                }

                float radius = (float)this.getWidth() / 2.0F - borderWidth;
                canvas.drawCircle((float)this.getWidth() / 2.0F, (float)this.getHeight() / 2.0F, radius, this.mViewPaint);
                break;
            case SQUARE:
                if (borderWidth > 0.0F) {
                    canvas.drawRect(borderWidth / 2.0F, borderWidth / 2.0F, (float)this.getWidth() - borderWidth / 2.0F - 0.5F, (float)this.getHeight() - borderWidth / 2.0F - 0.5F, this.mBorderPaint);
                }

                canvas.drawRect(borderWidth, borderWidth, (float)this.getWidth() - borderWidth, (float)this.getHeight() - borderWidth, this.mViewPaint);
            }
        } else {
            this.mViewPaint.setShader((Shader)null);
        }

    }

    static {
        DEFAULT_WAVE_SHAPE = WaveView.ShapeType.CIRCLE;
    }

    public static enum ShapeType {
        CIRCLE,
        SQUARE;

        private ShapeType() {
        }
    }
}
