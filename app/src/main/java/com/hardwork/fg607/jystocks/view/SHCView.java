package com.hardwork.fg607.jystocks.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.hardwork.fg607.jystocks.R;
import com.hardwork.fg607.jystocks.model.SHCData;

/**
 * Created by fg607 on 16-9-8.
 */
public class SHCView extends View {

    private static final String TAG = "SHCView";
    private String mTitle;
    private float mPoint;
    private float mChange;
    private float mChangeRatio;
    private int mTitleColor;
    private int mRiseColor;
    private int mFallColor;
    private float mTitleSize;
    private float mPointSize;
    private float mChangeSize;
    private Paint mPaint;
    private Rect mRect;
    private Rect mTextBound;

    private int mTitleWidth;
    private int mTitleHeight;
    private int mPointWidth;
    private int mPointHeight;
    private int mChangeWidth;
    private int mChangeHeight;
    private int mChangeRatWidth;
    private int mChangeRatHeight;
    private int mPointOffset = 20;
    private int mChangeRatOffset = 20;
    private int mUpBottomOffset = 10;

    private int mWidth;
    private int mHeight;

    private int mWidthMeasureSpec;
    private int mHeightMeasureSpec;

    public SHCView(Context context) {
        super(context);
    }

    public SHCView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SHCView);
        init(a);
    }

    public SHCView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SHCView);
        init(a);
    }

    private void init(TypedArray a){

        mTitle = a.getString(R.styleable.SHCView_titleText);
        mTitleSize = a.getDimension(R.styleable.SHCView_titleTextSize,10);
        mTitleColor = a.getColor(R.styleable.SHCView_titleTextColor,Color.BLACK);

        mPoint = a.getFloat(R.styleable.SHCView_pointNum,0);
        mPointSize = a.getDimension(R.styleable.SHCView_pointTextSize,10);

        mChange = a.getFloat(R.styleable.SHCView_changeNum,0);
        mChangeSize = a.getDimension(R.styleable.SHCView_changeTextSize,10);
        mChangeRatio = a.getFloat(R.styleable.SHCView_changeRatio,0);

        mRiseColor = a.getColor(R.styleable.SHCView_riseColor,Color.BLACK);
        mFallColor = a.getColor(R.styleable.SHCView_fallColor,Color.BLACK);

        a.recycle();

        mRect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;

        // 计算title宽高
        mPaint.setTextSize(mTitleSize);
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound);
        mTitleWidth = mTextBound.width();
        mTitleHeight = mTextBound.height();

        //计算point宽高
        mPaint.setTextSize(mPointSize);
        String point = "" + mPoint;
        mPaint.getTextBounds(point, 0, point.length(), mTextBound);
        mPointWidth = mTextBound.width();
        mPointHeight = mTextBound.height();

        //计算change宽高
        mPaint.setTextSize(mChangeSize);
        String change = "+" + mChange;
        mPaint.getTextBounds(change, 0, change.length(), mTextBound);
        mChangeWidth = mTextBound.width();
        mChangeHeight = mTextBound.height();

        //计算changeRatio宽高
        mPaint.setTextSize(mChangeSize);
        String changeRatio = "+" + mChangeRatio + "%";
        mPaint.getTextBounds(changeRatio, 0, changeRatio.length(), mTextBound);
        mChangeRatWidth = mTextBound.width();
        mChangeRatHeight = mTextBound.height();

        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {

            mWidth = specSize;

        } else {

            //上面一行的宽度
            int widthUpLine = getPaddingLeft() + getPaddingRight() + mTitleWidth +
                    mPointOffset + mPointWidth;

            //下面一行的宽度
            int widthBottomLine = getPaddingLeft() + getPaddingRight() + mChangeWidth +
                    mChangeRatOffset + mChangeRatWidth;

            if (specMode == MeasureSpec.AT_MOST) {

                int desire = Math.max(widthUpLine, widthBottomLine);
                mWidth = Math.min(desire, specSize);
            }
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {

            mHeight = specSize;

        } else {

            int desire = getPaddingTop() + getPaddingBottom() + Math.max(mTitleHeight, mPointHeight) +
                    mUpBottomOffset + mChangeHeight;

            if (specMode == MeasureSpec.AT_MOST) {

                mHeight = Math.min(desire, specSize);
            }

        }

        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        //第一行宽度多出距离
        int upDiff = mWidth-getPaddingLeft()-getPaddingRight()-mTitleWidth
                - mPointOffset - mPointWidth;

        //第二行宽度多出的距离
        int bottomDiff = mWidth-getPaddingLeft()-getPaddingRight()-mChangeWidth
                - mChangeRatOffset - mChangeRatWidth;

        //高度多出的距离
        int heightDiff = mHeight - getPaddingTop() - getPaddingBottom() -
                Math.max(mTitleHeight,mPointHeight) - mUpBottomOffset -
                mChangeHeight;

        //绘制title
        mPaint.setColor(mTitleColor);
        mPaint.setTextSize(mTitleSize);
        canvas.drawText(mTitle,getPaddingLeft()+upDiff/2, getPaddingTop()+heightDiff/2, mPaint);

        //绘制point
        if(mChange>0){
            mPaint.setColor(mRiseColor);
        }else {
            mPaint.setColor(mFallColor);
        }

        mPaint.setTextSize(mPointSize);
        canvas.drawText(""+mPoint,getPaddingLeft()+upDiff/2+mTitleWidth+mPointOffset,
                getPaddingTop()+heightDiff/2, mPaint);

        //绘制change
        mPaint.setTextSize(mChangeSize);
        canvas.drawText(mChange>0?"+"+mChange:""+mChange,getPaddingLeft()+upDiff/2, getPaddingTop()+
                heightDiff/2 + Math.max(mTitleHeight,mPointHeight) + mUpBottomOffset, mPaint);

        //绘制changeRatio
        canvas.drawText(mChangeRatio>0?"+"+mChangeRatio+"%":""+mChangeRatio+"%",getPaddingLeft()+upDiff/2+mChangeWidth+mChangeRatOffset, getPaddingTop()+
                heightDiff/2 + Math.max(mTitleHeight,mPointHeight) + mUpBottomOffset, mPaint);


    }

    public void setData(SHCData data){

        mPoint = data.getPrice();
        mChange = data.getChange();
        mChangeRatio = data.getChangeRatio();

        invalidate();

    }
}
