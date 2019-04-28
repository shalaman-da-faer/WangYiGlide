package com.lihy.knowledge.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lihy.knowledge.R;

/**
 * Created by lihy on 2019/4/8.
 */

public class PathMeasureView1 extends View {
    private static final String TAG = "PathMeasureView1";
    private float[] pos=new float[2];
    private float[] tan=new float[2];
    //箭头图像
    private Bitmap mBitmap;

    private Matrix matrix;

    private Path path;
    private Paint mPaint;
    private PathMeasure pathMeasure;

    private float mAnimatorValue=0.0f;

    //圆环半径
    private final int radius=200;

    private ValueAnimator animator;

    //控件尺寸
    private int width;
    private int height;
    public PathMeasureView1(Context context) {
        super(context);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=4;
        path=new Path();
        mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.arrow,options);
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        matrix=new Matrix();
        pathMeasure=new PathMeasure();
        animator= ValueAnimator.ofFloat(0,1f);
        animator.setDuration(30000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        matrix.reset();
        canvas.translate(width/2,height/2);
        path.reset();
        path.addCircle(0,0,radius, Path.Direction.CW);
        //绘制圆
        canvas.drawPath(path,mPaint);
        pathMeasure.setPath(path,false);
        //计算mAnimatorValue处的坐标和角度
        pathMeasure.getPosTan(mAnimatorValue*pathMeasure.getLength(),pos,tan);
        double degrees = Math.atan2(tan[1],tan[0])/Math.PI*180;
        Log.e(TAG, "onDraw: degress:"+degrees );
        //绘制箭头
        //        //进行角度旋转
        matrix.postRotate((float) degrees, mBitmap.getWidth()/2, mBitmap.getHeight() / 2);
//        matrix.postRotate(degrees , mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        matrix.postTranslate(-mBitmap.getWidth()/2+pos[0],-mBitmap.getHeight()/2+pos[1]);
        Log.e(TAG, "left: "+(width/2-mBitmap.getWidth()/2+pos[0]));
        canvas.drawBitmap(mBitmap,matrix,mPaint);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(!animator.isRunning()){
                animator.start();
            }
        }
        return true;
    }
}
