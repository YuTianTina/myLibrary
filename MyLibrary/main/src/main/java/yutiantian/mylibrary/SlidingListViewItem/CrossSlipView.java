package yutiantian.mylibrary.SlidingListViewItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.security.PublicKey;

/**
 * Created by 二更 on 2016/7/25.
 */
public class CrossSlipView extends FrameLayout {
    public View centerView,leftView,rightView;
    private Scroller operationScroller;
    public boolean isLeftExist,isRightExist;
    public int move_X;
    private int baseX;
    public static  int SLIDE_TO_OPEN_LEFT=0;
    public  static  int SLIDE_TO_OPEN_RIGHT=1;
    public DIRECTION direction=DIRECTION.CLOSE;
    public enum DIRECTION{
        SLIDE_TO_OPEN_LEFT,SLIDE_TO_OPEN_RIGHT,CLOSE;
    }

    public CrossSlipView(Context context) {
        super(context);
    }
    private void init(){
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if(isLeftExist){
            leftView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            addView(leftView);
        }
        centerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(centerView);
        if(isRightExist){
            rightView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            addView(rightView);
        }
    }
    public CrossSlipView(View centerView,View leftView, View rightView){
        super(centerView.getContext());
        this.centerView=centerView;
        this.leftView=leftView;
        this.rightView=rightView;
        operationScroller=new Scroller(centerView.getContext(),new BounceInterpolator());
        isLeftExist=(null!=leftView);
        isRightExist=(null!=rightView);
        init();
    }
}
