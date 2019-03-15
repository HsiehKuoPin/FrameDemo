package com.benjamin.widget.loading.error;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.benjamin.R;
import com.benjamin.widget.loading.OnRefreshingListener;


public class SmErrorLinearLayout extends ErrorLinearLayout implements View.OnClickListener {

    private ImageView ivDesc;
    private TextView tvDesc;
    private TextView tvRefresh;
    private OnRefreshingListener listener;
    private Drawable descDrawableOther;
    private Drawable descDrawableNetwork;
    private String descText;
    private String refreshText;
    private boolean globalClick;

    public SmErrorLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public SmErrorLinearLayout(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmErrorLinearLayout(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SmErrorLinearLayout);
            this.descDrawableOther = array.getDrawable(R.styleable.SmErrorLinearLayout_desc_image_other);
            this.descDrawableNetwork = array.getDrawable(R.styleable.SmErrorLinearLayout_desc_image_network);
            this.descText = array.getString(R.styleable.SmErrorLinearLayout_desc_text);
            this.refreshText = array.getString(R.styleable.SmErrorLinearLayout_refresh_text);
            this.globalClick = array.getInt(R.styleable.SmErrorLinearLayout_click_scope, 0) != 0;
            array.recycle();
        }
    }

    @Override
    public void setErrorMessage(String msg) {
        tvDesc.setText(msg);
        ivDesc.setImageDrawable(descDrawableOther);
    }

    @Override
    public void setNetworkErrorMessage(String msg) {
        tvDesc.setText(msg);
        ivDesc.setImageDrawable(descDrawableNetwork);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivDesc = (ImageView) getChildAt(0);
        tvDesc = (TextView) getChildAt(1);
        tvRefresh = (TextView) getChildAt(2);
        if (ivDesc == null || tvDesc == null || tvRefresh == null) {
            throw new IllegalStateException("SmErrorLinearLayout should have three child views,there are ImageView,TextView and TextView");
        }
        ivDesc.setImageDrawable(descDrawableOther);
        tvDesc.setText(descText);
        tvRefresh.setText(refreshText);
    }

    @Override
    public void setOnRefreshingListener(OnRefreshingListener listener) {
        this.listener = listener;
        if (globalClick) {
            super.setOnClickListener(this);
        } else {
            tvRefresh.setOnClickListener(this);
        }
    }

    public void setDescDrawableOther(Drawable descDrawableOther) {
        this.descDrawableOther = descDrawableOther;
    }

    public void setDescDrawableNetwork(Drawable descDrawableNetwork) {
        this.descDrawableNetwork = descDrawableNetwork;
    }

    public void setDescDrawableOther(int drawableId) {
        setDescDrawableOther(getResources().getDrawable(drawableId));
    }

    public void setDescDrawableNetwork(int drawableId) {
        setDescDrawableNetwork(getResources().getDrawable(drawableId));
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public void setRefreshText(String refreshText) {
        this.refreshText = refreshText;
    }

    public void setGlobalClick(boolean globalClick) {
        this.globalClick = globalClick;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onRefresh();
        }
    }
}
