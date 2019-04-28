package com.lihy.knowledge.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lihy on 2019/4/7.
 */

public class GuaGuaKa extends View {
    private Paint paint;
    private String gain="一等奖";

    private Path path;
    private float lastX;
    private float lastY;

    // create a bitmap with a circle, used for the "dst" image
    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
//        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setColor(0xFFFFCC44);
        c.drawColor(Color.GRAY);
//        c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p);
        return bm;
    }

    public GuaGuaKa(Context context) {
        super(context);
        init();
    }

    public GuaGuaKa(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuaGuaKa(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint=new Paint();
        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width=getWidth();
        int height=getHeight();
        //绘制奖品
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        Rect rect=new Rect();
        paint.getTextBounds(gain,0,gain.length()-1,rect);
        canvas.drawText(gain,(width-rect.right)/2,(height-rect.bottom)/2,paint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(0,0,width,height,paint);
        }
        canvas.drawBitmap(makeDst(width,height),0,0,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        //绘制路径
        paint.setStrokeWidth(20);
        canvas.drawPath(path,paint);
        paint.setXfermode(null);

//        canvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=event.getX();
                lastY=event.getY();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(lastX,lastY,event.getX(),event.getY());
                lastX=event.getX();
                lastY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
