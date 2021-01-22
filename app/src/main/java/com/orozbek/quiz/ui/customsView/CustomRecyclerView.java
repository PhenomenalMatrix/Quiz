package com.orozbek.quiz.ui.customsView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerView extends RecyclerView {
    private boolean isScrollEnabled = true;

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollEnabled(boolean flag){
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return isScrollEnabled&&super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return isScrollEnabled&& super.onInterceptTouchEvent(event);
    }
}
