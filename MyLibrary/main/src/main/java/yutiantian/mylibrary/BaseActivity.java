package yutiantian.mylibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 二更 on 2016/7/25.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=this;
        //测试
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            View decorView=getWindow().getDecorView();
//            int option=View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
//            WindowManager.LayoutParams localLayoutParams=getWindow().getAttributes();
//            localLayoutParams.flags=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|localLayoutParams.flags;
//        }
    }
}
