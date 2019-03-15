
package com.benjamin.widget.loading.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.benjamin.R;
import com.benjamin.widget.loading.LoadingV;
import com.benjamin.widget.loading.OnRefreshingListener;

public class DialogLoadingV implements LoadingV {

    private Dialog loadingDialog;
    private int viewType = TYPE_CONTENT_VIEW;
    private Toast toast;
    private OnRefreshingListener onRefreshListener = OnRefreshingListener.DEFAULT_IMPL;

    public DialogLoadingV(@NonNull Context context, int progressLayoutId) {
        this(LayoutInflater.from(context).inflate(progressLayoutId, null));
    }

    public DialogLoadingV(@NonNull View progressView) {
        this(progressView, R.style.LoadingDialogUnTran);
    }

    public DialogLoadingV(@NonNull Context context, int progressLayoutId, int themeId) {
        this(LayoutInflater.from(context).inflate(progressLayoutId, null), themeId);
    }

    public DialogLoadingV(@NonNull View progressView, int themeId) {
        this.loadingDialog = new LoadingDialog(progressView.getContext(), progressView, themeId);
    }

    @Override
    public void showProgressView() {
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
            viewType = TYPE_PROGRESS_VIEW;
            onRefreshListener.onRefresh();
        }
    }

    @Override
    public void showErrorView(String errorMsg) {
        show(errorMsg);
        showContentView();
    }

    @Override
    public void showNetworkErrorView(String errorMsg) {
        show(errorMsg);
        showContentView();
    }

    @Override
    public void showEmptyView() {
        showContentView();
    }

    public void setToast(Toast toast) {
        this.toast = toast;
    }

    private void show(String text) {
        if (toast != null) {
            toast.setText(text);
            toast.show();
        }
    }

    @Override
    public void showContentView() {
        loadingDialog.dismiss();
        viewType = TYPE_CONTENT_VIEW;
    }

    public void setLoadingDialog(Dialog loadingDialog) {
        if (loadingDialog != null) {
            this.loadingDialog = loadingDialog;
        }
    }

    public Dialog getLoadingDialog() {
        return loadingDialog;
    }

    @Override
    public int currentViewType() {
        return viewType;
    }

    @Override
    public boolean isProgressShowing() {
        return currentViewType() == TYPE_PROGRESS_VIEW;
    }

    @Override
    public void setOnRefreshingListener(OnRefreshingListener onRefreshListener) {
        if (onRefreshListener == null) {
            return;
        }
        this.onRefreshListener = onRefreshListener;
    }
}
