package yutiantian.mylibrary.animator;

import android.animation.TypeEvaluator;

/**
 * Created by Tina on 2016/9/21.
 * Description:
 */

public class ColorEvaluator implements TypeEvaluator{
    private int currentR=-1;
    private int currentG=-1;
    private int currentB=-1;
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor= (String) startValue;
        int startR=Integer.parseInt(startColor.substring(1,3),16);
        int startG=Integer.parseInt(startColor.substring(3,5),16);
        int startB=Integer.parseInt(startColor.substring(5,7),16);
        String endColor= (String) endValue;
        int endR=Integer.parseInt(endColor.substring(1,3),16);
        int endG=Integer.parseInt(endColor.substring(3,5),16);
        int endB=Integer.parseInt(endColor.substring(5,7),16);
        if(currentR==-1){
            currentR=startR;
        }
        if(currentG==-1){
            currentG=startG;
        }
        if(currentB==-1){
            currentB=startB;
        }
        int RDiff=Math.abs(endR-startR);
        int BDiff=Math.abs(endB-startB);
        int GDiff=Math.abs(endG-startG);
        int colorDiff=RDiff+BDiff+GDiff;
        if(currentR!=endR)
            currentR=getCurrentColor(startR,endR,colorDiff,fraction,0);
        else if(currentG!=endG)
            currentG=getCurrentColor(startG,endG,colorDiff,fraction,RDiff);
        else if(currentB!=endB)
            currentB=getCurrentColor(startB,endB,colorDiff,fraction,RDiff+GDiff);
        return "#"+getHexString(currentR)+getHexString(currentG)+getHexString(currentB);
    }
    /**
     * 根据fraction计算现在颜色
     * */
    private int getCurrentColor(int startColor,int endColor,int diff,float fraction,int offset){
        int currentColor;
        if(startColor>=endColor){
            currentColor= (int) (startColor+(fraction*diff-offset));
            if(currentColor>endColor)
                currentColor=endColor;
        }else{
            currentColor= (int) (startColor-(fraction*diff-offset));
            if(currentColor<endColor)
                currentColor=endColor;
        }
        return currentColor;
    }
    /**
     * 将十进制转为十六进制字符串
     * */
    private String getHexString(int value){
        String hexString=Integer.toHexString(value);
        if(hexString.length()==1){
            hexString="0"+hexString;
        }
        return hexString;
    }
}
