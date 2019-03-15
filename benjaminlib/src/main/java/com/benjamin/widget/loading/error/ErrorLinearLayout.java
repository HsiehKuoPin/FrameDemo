package com.benjamin.widget.loading.error;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.benjamin.widget.loading.OnRefreshingListener;


public abstract class ErrorLinearLayout extends LinearLayout{

    public ErrorLinearLayout(Context context) {
        this(context, null);
    }

    public ErrorLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setErrorMessage(String msg);

    public abstract void setNetworkErrorMessage(String msg);

    public abstract void setOnRefreshingListener(OnRefreshingListener listener);

}
