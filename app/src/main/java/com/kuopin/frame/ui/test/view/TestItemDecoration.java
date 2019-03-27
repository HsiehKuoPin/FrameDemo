package com.kuopin.frame.ui.test.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TestItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private int spanSize;


    public TestItemDecoration(int spanCount, int spanSize, int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.spanSize = spanSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item c
        int width = parent.getWidth();
        int childWidth = (width/spanSize)-spacing;
        int tmp = (width-spacing*(spanCount-1)-childWidth*spanCount)/2;

        if(spanCount == 1){
            outRect.left = tmp; //
            outRect.right = tmp; //
        }else {
            if (column == 0) {
                outRect.left = tmp; //
                outRect.right = spacing / 2;
            } else if (column == (spanCount - 1)) {
                outRect.left = spacing / 2; //
                outRect.right = tmp;
            } else {
                outRect.left = spacing / 2; //
                outRect.right = spacing / 2;
            }
        }

        if (position >= spanCount) {
            outRect.top = spacing / 2; // item top
        }
    }
}