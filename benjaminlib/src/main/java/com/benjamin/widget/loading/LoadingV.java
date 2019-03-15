package com.benjamin.widget.loading;
import android.support.annotation.IntDef;

public interface LoadingV {

    int TYPE_CONTENT_VIEW = 0;
    int TYPE_ERROR_VIEW = 1;
    int TYPE_EMPTY_VIEW = 2;
    int TYPE_PROGRESS_VIEW = 3;

    void showProgressView();

    void showErrorView(String errorMsg);

    void showNetworkErrorView(String errorMsg);

    void showEmptyView();

    void showContentView();

    int currentViewType();

    boolean isProgressShowing();

    void setOnRefreshingListener(OnRefreshingListener onRefreshListener);

    @IntDef({TYPE_CONTENT_VIEW, TYPE_EMPTY_VIEW, TYPE_ERROR_VIEW, TYPE_PROGRESS_VIEW})
    @interface ViewType {

    }

    LoadingV DEFAULT_IMPL = new LoadingV() {

        private int viewType = TYPE_CONTENT_VIEW;

        @Override
        public void showProgressView() {
            viewType = TYPE_PROGRESS_VIEW;
        }

        @Override
        public void showErrorView(String errorMsg) {
            viewType = TYPE_ERROR_VIEW;
            showContentView();
        }

        @Override
        public void showNetworkErrorView(String errorMsg) {
            viewType = TYPE_ERROR_VIEW;
            showContentView();
        }

        @Override
        public void showEmptyView() {
            viewType = TYPE_EMPTY_VIEW;
            showContentView();
        }

        @Override
        public void showContentView() {
            viewType = TYPE_CONTENT_VIEW;
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

        }
    };
}
