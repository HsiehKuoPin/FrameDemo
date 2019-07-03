package com.onechat.cat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import com.onechat.cat.R;

/**
 * @author Ben
 * @date 2019/7/3
 */
public class DrawableRadioButton extends AppCompatRadioButton {

    private int leftDrawableWidth, leftDrawableHeight, rightDrawableWidth, rightDrawableHeight,
            topDrawableWidth, topDrawableHeight, bottomDrawableWidth, bottomDrawableHeight;

    private final int DRAWABLE_LEFT = 0;
    private final int DRAWABLE_TOP = 1;
    private final int DRAWABLE_RIGHT = 2;
    private final int DRAWABLE_BOTTOM = 3;

    public DrawableRadioButton(Context context) {
        this(context,null);
    }

    public DrawableRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawableRadioButton, defStyleAttr, 0);

        leftDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_leftDrawableHeight,-1);
        leftDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_leftDrawableWidth,-1);

        rightDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_rightDrawableHeight,-1);
        rightDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_rightDrawableWidth,-1);

        topDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_topDrawableHeight,-1);
        topDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_topDrawableWidth,-1);

        bottomDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_bottomDrawableHeight,-1);
        bottomDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.DrawableRadioButton_bottomDrawableWidth,-1);

        typedArray.recycle();

        Drawable[] drawables = getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            setDrawableSize(drawables[i], i);
        }
        setCompoundDrawables(drawables[DRAWABLE_LEFT], drawables[DRAWABLE_TOP], drawables[DRAWABLE_RIGHT], drawables[DRAWABLE_BOTTOM]);
    }

    /**
     * 设置图片的高度和宽度
     * @param drawable
     * @param index
     */
    private void setDrawableSize(Drawable drawable, int index) {
        if (drawable == null) return;
        int width = 0, height = 0;
        switch (index) {
            case DRAWABLE_LEFT:
                width = leftDrawableWidth;
                height = leftDrawableHeight;
                break;
            case DRAWABLE_TOP:
                width = topDrawableWidth;
                height = topDrawableHeight;
                break;
            case DRAWABLE_RIGHT:
                width = rightDrawableWidth;
                height = rightDrawableHeight;
                break;
            case DRAWABLE_BOTTOM:
                width = bottomDrawableWidth;
                height = bottomDrawableHeight;
                break;
        }
        //如果没有设置图片的高度和宽度具使用默认的图片高度和宽度
        if (width < 0) width = drawable.getIntrinsicWidth();
        if (height < 0) height = drawable.getIntrinsicHeight();

        drawable.setBounds(0, 0, width, height);
    }

}
