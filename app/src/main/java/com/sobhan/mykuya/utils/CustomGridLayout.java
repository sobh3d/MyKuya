package com.sobhan.mykuya.utils;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class CustomGridLayout extends GridLayoutManager {
    private boolean isScrollEnabled = false;

    public CustomGridLayout(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
