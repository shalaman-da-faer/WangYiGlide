package com.lihy.knowledge.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.lihy.knowledge.R;

/**
 * Created by lihy on 2019/4/18.
 */

public class PathMeasureTest extends View{
    private Bitmap mBitmap;
    private Path path;

    private Paint mPaint;

    private int radius=200;

    private Matrix matrix=new Matrix();
    public PathMeasureTest(Context context) {
        super(context);
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=4;
        mBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.arrow,options);
        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        int width=getWidth();
        int height=getHeight();
        canvas.drawLine(0,height/2,width,height/2,mPaint);
        canvas.drawLine(width/2,0,width/2,height,mPaint);
        path.addCircle(width/2,height/2,radius, Path.Direction.CW);
        canvas.drawPath(path,mPaint);
        canvas.translate(width/2,height/2);

        canvas.drawBitmap(mBitmap,radius-mBitmap.getWidth()/2,2-mBitmap.getHeight()/2,mPaint);
        matrix.reset();

//        matrix.setTranslate(width/2+radius-mBitmap.getWidth()/2,height/2-mBitmap.getHeight()/2);
        matrix.postRotate(90,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        matrix.postTranslate(-mBitmap.getWidth()/2,radius-mBitmap.getHeight()/2);
        canvas.drawBitmap(mBitmap,matrix,mPaint);
    }
}
