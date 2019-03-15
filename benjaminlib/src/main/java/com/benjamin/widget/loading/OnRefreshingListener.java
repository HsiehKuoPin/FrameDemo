package com.benjamin.widget.loading;

public interface OnRefreshingListener {
    void onRefresh();

    OnRefreshingListener DEFAULT_IMPL = new OnRefreshingListener() {
        @Override
        public void onRefresh() {

        }
    };
}