package com.benjamin.widget.loading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benjamin.R;
import com.benjamin.widget.loading.dialog.LogoDialogLoadingV;
import com.benjamin.widget.loading.embedded.EmbeddedLoadingV;
import com.benjamin.widget.loading.progress.LogoProgressView;

public class LoadingVFactory {

    public LoadingV createDialogDefaultLoadingV(Context context) {
        return new LogoDialogLoadingV(context);
    }

    public LoadingV createEmbeddedDefaultLoadingV(ViewGroup loadingLayout) {
        return new EmbeddedLoadingV
                .Builder(loadingLayout)
                .setProgressView(new LogoProgressView(loadingLayout.getContext()))
                .setErrorViewId(R.layout.layout_error)
                .setEmptyViewId(R.layout.layout_empty)
                .build();
    }

    public LoadingV createEmbeddedDefaultLoadingV(ViewGroup loadingLayout, String emptyDesc) {
        View emptyView = LayoutInflater.from(loadingLayout.getContext()).inflate(R.layout.layout_empty, loadingLayout, false);
        TextView tvEmptyDesc = emptyView.findViewById(R.id.tv_empty_desc);
        tvEmptyDesc.setText(emptyDesc);
        return new EmbeddedLoadingV
                .Builder(loadingLayout)
                .setProgressView(new LogoProgressView(loadingLayout.getContext()))
                .setErrorViewId(R.layout.layout_error)
                .setEmptyView(emptyView)
                .build();
    }

}
