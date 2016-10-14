package yutiantian.mylibrary.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tina on 2016/9/20.
 * Description:
 */

public class MyAnimView extends View{

    public static  final float RADIUS=50f;
    private Point currentPoint;
    private Paint mPaint;
    private  String color;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(currentPoint==null){
            currentPoint=new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnim();
        }else{
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas){
        float x=currentPoint.getX();
        float y=currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    private void startAnim(){
        Point startPoint=new Point(RADIUS,RADIUS);
        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint= (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        String startColor="#0000FF";
        String endColor="#FF0000";
        ObjectAnimator objectAnimator=ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),startColor,endColor);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(5000);
        animatorSet.play(valueAnimator).with(objectAnimator);
        animatorSet.start();
    }
}
