package com.hardwork.fg607.jystocks.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ToggleButton;

import com.hardwork.fg607.jystocks.R;

/**
 * Created by fg607 on 16-9-7.
 */
public class BottomMenuBtn extends ToggleButton{

    private static final String TAG = "BottomMenuBtn";
    private int mTextColor;
    private int mTextColorPressed;
    private Bitmap mImageNormal;
    private Bitmap mImagePressed;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Rect mRect;
    private Rect mTextBound;
    private String mTitle;


    public BottomMenuBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomMenuBtn);
        init(a);
    }


    public BottomMenuBtn(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.BottomMenuBtn);
        init(a);
    }

    public BottomMenuBtn(Context context) {
        super(context);
    }

    private void init(TypedArray a) {
        mTextColorPressed = a.getColor(R.styleable.BottomMenuBtn_textColorPressed, Color.BLACK);
        mImageNormal = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.BottomMenuBtn_imageNormal, 0));
        mImagePressed = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.BottomMenuBtn_imagePressed, 0));
        mTitle = a.getString(R.styleable.BottomMenuBtn_titleText);

        a.recycle();

        mTextColor = getCurrentTextColor();
        mRect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(getTextSize());

        // 计算title宽高
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY){

            mWidth = specSize;

        } else {

            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImageNormal.getWidth();
            // 由字体决定的宽
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();

            if (specMode == MeasureSpec.AT_MOST){

                int desire = Math.max(desireByImg, desireByTitle);
                mWidth = Math.min(desire, specSize);
            }
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY){

            mHeight = specSize;

        } else {

            int desire = getPaddingTop() + getPaddingBottom() + mImageNormal.getHeight() + mTextBound.height();

            if (specMode == MeasureSpec.AT_MOST){

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

        //高度空白距离
        int diff = mHeight-getPaddingTop()-getPaddingBottom()-mTextBound.height()
                -mTextBound.width();

        if(isChecked()){

            mPaint.setColor(mTextColorPressed);
            mPaint.setStyle(Paint.Style.FILL);

        }else {

            mPaint.setColor(mTextColor);
            mPaint.setStyle(Paint.Style.FILL);
        }

        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (mTextBound.width() > mWidth){

            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else{

            //正常情况，将字体居中
            canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom()-diff/4, mPaint);
        }

        mRect.bottom -= mTextBound.height();

        //计算图片绘制尺寸(图片宽高等于文字宽度)
        mRect.left = mWidth / 2 - mTextBound.width() / 2;
        mRect.right = mWidth / 2 + mTextBound.width() / 2;
        mRect.top = getPaddingTop()+diff/4;
        mRect.bottom = mRect.right - mRect.left + mRect.top;



        if(isChecked()){

            canvas.drawBitmap(mImagePressed, null, mRect, mPaint);

        }else {


            canvas.drawBitmap(mImageNormal, null, mRect, mPaint);
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
