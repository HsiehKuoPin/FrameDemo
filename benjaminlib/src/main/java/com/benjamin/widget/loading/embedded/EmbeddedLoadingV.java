package com.benjamin.widget.loading.embedded;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.benjamin.widget.loading.LoadingV;
import com.benjamin.widget.loading.OnRefreshingListener;
import com.benjamin.widget.loading.error.ErrorLinearLayout;

public class EmbeddedLoadingV implements LoadingV {
    private static final String TAG = "EmbeddedLoadingV";

    private ViewGroup contentView;

    private FrameLayout othersView;

    private FrameLayout.LayoutParams flp;

    private ErrorLinearLayout errorView;

    private View emptyView;

    private View ProgressView;

    private OnRefreshingListener onRefreshListener = OnRefreshingListener.DEFAULT_IMPL;

    private EmbeddedLoadingV() {

    }

    private void setContentView(@NonNull ViewGroup contentView) {
        this.contentView = contentView;
        View others = this.contentView.findViewWithTag("others");
        if (others != null) {
            this.othersView = (FrameLayout) others;
        } else {
            this.othersView = new FrameLayout(contentView.getContext());
            othersView.setTag("others");
        }
        this.flp = new FrameLayout.LayoutParams(-1, -1);
        flp.gravity = Gravity.CENTER;
    }

    private void setErrorView(ErrorLinearLayout errorView) {
        if (errorView != null) {
            errorView.setTag("error");
            this.errorView = errorView;
        }
    }

    private void setEmptyView(View emptyView) {
        if (emptyView != null) {
            emptyView.setTag("empty");
            this.emptyView = emptyView;
        }
    }

    private void setProgressView(View progressView) {
        if (progressView != null) {
            progressView.setTag("progress");
            this.ProgressView = progressView;
        }
    }

    @Override
    public void showContentView() {
        if (currentViewType() != TYPE_CONTENT_VIEW) {
            doShowView(contentView);
            Log.d(TAG, "show content view");
        }
    }

    @Override
    public int currentViewType() {
        int type = TYPE_CONTENT_VIEW;
        if (othersView != null) {
            View typeView = othersView.getChildAt(0);
            View lastView = contentView.getChildAt(contentView.getChildCount() - 1);
            if (lastView == othersView && typeView != null) {
                String tag = (String) typeView.getTag();
                switch (tag) {
                    case "error":
                        type = TYPE_ERROR_VIEW;
                        break;
                    case "empty":
                        type = TYPE_EMPTY_VIEW;
                        break;
                    case "progress":
                        type = TYPE_PROGRESS_VIEW;
                        break;
                    default:
                        break;
                }
            }
        }
        return type;
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

    @Override
    public void showEmptyView() {
        if (currentViewType() == TYPE_EMPTY_VIEW) {
            Log.w(TAG, "the emptyView is showing");
            return;
        }
        if (emptyView != null) {
            doShowView(emptyView);
            Log.d(TAG, "show empty view");
        } else {
            showContentView();
            Log.w(TAG, "the emptyView is null");
        }
    }

    @Override
    public void showProgressView() {
        if (currentViewType() == TYPE_PROGRESS_VIEW) {
            Log.w(TAG, "the progressView is showing");
            return;
        }
        if (ProgressView != null) {
            doShowView(ProgressView);
            onRefreshListener.onRefresh();
            Log.d(TAG, "show progress view");
        } else {
            Log.w(TAG, "the progressView is null");
            showContentView();
        }
    }

    @Override
    public void showErrorView(String errorMsg) {
        changeErrorView(errorMsg, 0);
    }

    @Override
    public void showNetworkErrorView(String errorMsg) {
        changeErrorView(errorMsg, 1);
    }

    private void changeErrorView(String errorMsg, int type) {
        if (errorView != null) {
            if (type == 0) {
                errorView.setErrorMessage(errorMsg);
            } else {
                errorView.setNetworkErrorMessage(errorMsg);
            }
            errorView.setOnRefreshingListener(onRefreshListener);
            doShowView(errorView);
            Log.d(TAG, "show error view");
        } else {
            showContentView();
            Log.w(TAG, "the errorView is null");
        }
    }

    private void doShowView(@NonNull View view) {
        if (view == contentView) {
            if ("others".equals(contentView.getChildAt(contentView.getChildCount() - 1).getTag())) {
                contentView.removeViewAt(contentView.getChildCount() - 1);
                hideContentChildView(false);
            }
        } else {

            hideContentChildView(true);

            if (contentView.findViewWithTag("others") == null) {
                contentView.addView(othersView, -1, -1);
            }

            othersView.setVisibility(View.VISIBLE);
            othersView.removeAllViews();
            view.setLayoutParams(flp);
            othersView.addView(view);
        }
    }

    private void hideContentChildView(boolean hide) {
        if (hide) {
            for (int i = 0; i < contentView.getChildCount(); i++) {
                View childView = contentView.getChildAt(i);
                childView.setVisibility(View.GONE);
            }
        } else {
            for (int i = 0; i < contentView.getChildCount(); i++) {
                contentView.getChildAt(i).setVisibility(View.VISIBLE);
            }
        }
    }

    public static class Builder {

        private final LayoutInflater inflater;

        private ViewGroup contentView;

        private View progressView;

        private ErrorLinearLayout errorView;

        private View emptyView;

        public Builder(@NonNull ViewGroup contentView) {
            this.contentView = contentView;
            inflater = (LayoutInflater) contentView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public Builder setProgressView(View progressView) {
            this.progressView = progressView;
            return this;
        }

        public Builder setErrorView(ErrorLinearLayout errorView) {
            this.errorView = errorView;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            this.emptyView = emptyView;
            return this;
        }

        public Builder setProgressViewId(int progressViewId) {
            this.progressView = inflater.inflate(progressViewId, null);
            return this;
        }

        public Builder setErrorViewId(int errorViewId) {
            this.errorView = (ErrorLinearLayout) inflater.inflate(errorViewId, null);
            return this;
        }

        public Builder setEmptyViewId(int emptyViewId) {
            this.emptyView = inflater.inflate(emptyViewId, null);
            return this;
        }

        public EmbeddedLoadingV build() {
            EmbeddedLoadingV loadingView = new EmbeddedLoadingV();
            loadingView.setContentView(contentView);
            loadingView.setProgressView(progressView);
            loadingView.setErrorView(errorView);
            loadingView.setEmptyView(emptyView);
            return loadingView;
        }
    }
}