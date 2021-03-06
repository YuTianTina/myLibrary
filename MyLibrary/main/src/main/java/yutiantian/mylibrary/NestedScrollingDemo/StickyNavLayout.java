package yutiantian.mylibrary.NestedScrollingDemo;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by 二更 on 2016/8/15.
 */
public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {
    private int mTopViewHeight;

    public StickyNavLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
//        return super.onStartNestedScroll(child, target, nestedScrollAxes);
        return  (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
//        super.onNestedPreScroll(target, dx, dy, consumed);
        boolean hiddenTop=dy>0&&getScrollY()<mTopViewHeight;
        boolean showTop=dy<0&&getScrollY()>0&&ViewCompat.canScrollVertically(target,-1);
        if(hiddenTop||showTop){
            scrollBy(0,dy);
            consumed[1]=dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
//        return super.onNestedFling(target, velocityX, velocityY, consumed);
        if(getScrollY()>=mTopViewHeight)
            return  false;
        fling((int) velocityY);
        return true;
    }
    OverScroller mScroller;
    private void fling(int velocityY) {
        mScroller.fling(0,getScrollY(),0,velocityY,0,0,0,mTopViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if(y<0)
            y=0;
        if(y>mTopViewHeight)
            y=mTopViewHeight;
        if(y!=getScrollY())
            super.scrollTo(x,y);
    }

}
