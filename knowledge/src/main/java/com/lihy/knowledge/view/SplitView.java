package com.lihy.knowledge.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lihy.knowledge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihy on 2019/3/25.
 */

public class SplitView extends View {
    private Bitmap mBitmap;
    private Paint mPaint;
    private List<Ball> mBalls;
    private float d =8;
    private ValueAnimator animator;
    public SplitView(Context context) {
        this(context,null);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.me_small);
        mPaint=new Paint();
        mBalls=new ArrayList<>();
        int width= mBitmap.getWidth();
        int height=mBitmap.getHeight();
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Ball ball=new Ball();
                ball.color=mBitmap.getPixel(i,j);
                ball.x= i*d + d/2;
                ball.y= j*d + d/2;
                ball.r=d/2;
                //速度(-20,20)
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);
                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;
                mBalls.add(ball);
            }
        }
        mBitmap.recycle();
        mBitmap=null;
        animator=ValueAnimator.ofFloat(0,1);
        animator.setDuration(5000000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float a= (float) animation.getAnimatedValue();
                updateBall();
            }
        });
    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateBall() {
        for(Ball ball : mBalls){
            ball.x+=ball.vX;
            ball.y+=ball.vY;
            ball.vX+=ball.aX;
            ball.vY+=ball.aY;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(200,200);
        for(int i=0;i<mBalls.size();i++){
            Ball ball=mBalls.get(i);
            mPaint.setColor(ball.color);
            canvas.drawCircle(ball.x,ball.y,ball.r,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            animator.start();
        }
        return super.onTouchEvent(event);
    }
}
