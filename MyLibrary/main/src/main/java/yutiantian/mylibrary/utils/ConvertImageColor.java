package yutiantian.mylibrary.utils;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/**
 * 基本功能：转换图片颜色工具
 */
public class ConvertImageColor {
    Activity activity;

    public ConvertImageColor(Activity activity) {
        this.activity = activity;
    }

    // 改变图标的颜色
    public  Drawable changeImageColor(int drawable,int color){
        Drawable myIcon = activity.getResources().getDrawable(drawable);
        myIcon.setColorFilter(activity.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
        return  myIcon;
    }
}
