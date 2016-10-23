package com.hardwork.fg607.jystocks.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;

import com.hardwork.fg607.jystocks.R;
import com.hardwork.fg607.jystocks.adapter.StockListAdapter;

/**
 * Created by fg607 on 16-10-16.
 */
public class MyRecyclerView extends RecyclerView {

    private float mStartX;
    private float mStartY;
    private float mDx = 0;
    private float mDy = 0;
    private int mScrollX;
    private boolean mScrolling= false;
    private boolean mCanScroll = true;
    private boolean mUpdateScroll = false;
    private Thread mThread;
    private StockListAdapter mAdapter;
    private HorizontalScrollView mScrollView;

    public MyRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context){

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                    if(mUpdateScroll && mScrollView != null){

                        mScrollX = mScrollView.getScrollX();
                        mAdapter = (StockListAdapter) getAdapter();
                        mAdapter.setmScrollX(mScrollX);
                    }
                }

            }
        });

        mThread.start();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getRawX();
        float y = e.getRawY();

        if(e.getAction()!=MotionEvent.ACTION_MOVE){

            for (int i = 0; i < getChildCount(); i++) {

                mScrollView = (HorizontalScrollView) getChildAt(i).findViewById(R.id.scroll);

                mScrollView.onTouchEvent(e);

            }

        }

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mStartX = x;
                mStartY = y;
                mCanScroll = true;
                mUpdateScroll = false;
                break;
            case MotionEvent.ACTION_MOVE:

                mDx = x - mStartX;
                mDy = y - mStartY;


                if(!mScrolling && Math.abs(mDx)>Math.abs(mDy)) {

                   for (int i = 0; i < getChildCount(); i++) {

                        mScrollView = (HorizontalScrollView) getChildAt(i).findViewById(R.id.scroll);

                        mScrollView.onTouchEvent(e);

                    }

                    mScrollX = mScrollView.getScrollX();
                    mAdapter = (StockListAdapter) getAdapter();
                    mAdapter.setmScrollX(mScrollX);

                    mCanScroll = false;

                    return true;

                }

                if(!mCanScroll){

                    return false;
                }

                break;

            case MotionEvent.ACTION_UP:
                mUpdateScroll = true;
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUpdateScroll = false;
                    }
                },4000);
                break;

        }


        return super.onTouchEvent(e);
    }

    @Override
    public void onScrollStateChanged(int state) {

        if(state!=SCROLL_STATE_IDLE){
            mScrolling = true;
        }else {

            mScrolling = false;
        }
        super.onScrollStateChanged(state);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        mScrollView = (HorizontalScrollView) getChildAt(0).findViewById(R.id.scroll);
        mScrollView.scrollTo(mScrollX,0);


        mScrollView = (HorizontalScrollView) getChildAt(getChildCount()-1).findViewById(R.id.scroll);
        mScrollView.scrollTo(mScrollX,0);

        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }
}
