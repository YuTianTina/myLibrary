package yutiantian.mylibrary.animator;

import android.animation.TypeEvaluator;

/**
 * Created by Tina on 2016/9/20.
 * Description:
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint= (Point) startValue;
        Point endPoint= (Point) endValue;
        float x=startPoint.getX()+fraction*(endPoint.getX()-startPoint.getX());
        float y=startPoint.getY()+fraction*(endPoint.getY()-startPoint.getY());

        return new Point(x,y);
    }
}
