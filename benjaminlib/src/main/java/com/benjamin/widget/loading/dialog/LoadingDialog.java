package com.benjamin.widget.loading.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

public class LoadingDialog extends Dialog {

    private final View progressView;

    public LoadingDialog(@NonNull Context context, @NonNull View progressView, int themeId) {
        super(context, themeId);
        this.progressView = progressView;
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(progressView);
    }

}
