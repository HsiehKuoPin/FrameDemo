package com.kuopin.frame.ui.test.view;

import android.support.v7.widget.GridLayoutManager;

public class TestSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    int spanCount = 0;

    public TestSpanSizeLookup(int spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        int[][] count = {{24, 0, 0, 0}, {12, 12, 0, 0}, {9, 6, 9, 0}, {6, 6, 6, 6}};
        int tmp = position % spanCount;
        return count[spanCount - 1][tmp];
    }
}
