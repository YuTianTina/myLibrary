package yutiantian.mylibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yutiantian.mylibrary.BlueTooth.BlueTooth;
import yutiantian.mylibrary.SlidingTabLayout.AndroidSlidingTabLayoutDemo;
import yutiantian.mylibrary.StickyListHeaders.StickyHeaderListDemo;
import yutiantian.mylibrary.baseRecycle.RecyclerBaseView;
import yutiantian.mylibrary.demo.MarqueeViewDemo;
import yutiantian.mylibrary.qrcode.QRCodeDemo2;

public class MainActivity extends BaseActivity {

    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.btn_marquee_go)
    Button btnMarqueeGo;
    @Bind(R.id.btn_sliding_tab_layout)
    Button btnSlidingTabLayout;
    @Bind(R.id.btn_sticky_header_list)
    Button btnStickyHeaderList;
    @Bind(R.id.btn_recycle)
    Button btnRecycle;
    @Bind(R.id.btn_bluetooth)
    Button btnBluetooth;
    @Bind(R.id.btn_qrcode)
    Button btnQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        int[] colors = getResources().getIntArray(R.array.progress_colors);
        refreshLayout.setColorSchemeColors(colors);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3 * 1000);
//                animatortest();
//                animatortest2();
//                animatortest3();
//                animatortest4();
                animatortest5();
            }
        });




    }
    /*-------------属性动画-------------*/
    //透明度
    private void animatortest(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(btnQrcode,"alpha",1f,0f,1f);
        animator.setDuration(500);
        animator.start();
    }
    //旋转
    private void animatortest2(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(btnQrcode,"rotation",0f,180f,0f);
        animator.setDuration(5*1000);
        animator.start();
    }
    //向左平移
    private void animatortest3(){
        float x=btnQrcode.getTranslationX();
        ObjectAnimator animator=ObjectAnimator.ofFloat(btnQrcode,"translationX",x,-500f,x);
        animator.setDuration(5*1000);
        animator.start();
    }
    //缩放
    private void animatortest4(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(btnQrcode,"scaleY",1f,3f,1f);
        animator.setDuration(5*1000);
        animator.start();
    }
    /*-------------组合动画-------------*/
    private void animatortest5(){
        float x=btnQrcode.getTranslationX();
        ObjectAnimator animator1=ObjectAnimator.ofFloat(btnQrcode,"alpha",1f,0f,1f);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(btnQrcode,"rotation",0f,180f,0f);
        ObjectAnimator animator3=ObjectAnimator.ofFloat(btnQrcode,"translationX",x,-500f,x);
        ObjectAnimator animator4=ObjectAnimator.ofFloat(btnQrcode,"scaleY",1f,3f,1f);
        
        animator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(MainActivity.this, "平移动画结束", Toast.LENGTH_SHORT).show();
            }
        });
        
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(5000);
        animatorSet.play(animator1).with(animator2).before(animator3).after(animator4);
        animatorSet.setDuration(5*1000);
        animatorSet.start();
    }

    @OnClick({R.id.btn_marquee_go, R.id.btn_sliding_tab_layout, R.id.btn_sticky_header_list, R.id.btn_recycle, R.id.btn_bluetooth, R.id.btn_qrcode})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_marquee_go:
                Intent i = new Intent();
                i.setClass(this, MarqueeViewDemo.class);
                startActivity(i);
                break;
            case R.id.btn_sliding_tab_layout:
                startActivity(new Intent(this, AndroidSlidingTabLayoutDemo.class));
                break;
            case R.id.btn_sticky_header_list:
                startActivity(new Intent(this, StickyHeaderListDemo.class));
                break;
            case R.id.btn_recycle:
                startActivity(new Intent(this, RecyclerBaseView.class));
                break;
            case R.id.btn_bluetooth:
                startActivity(new Intent(this, BlueTooth.class));
                break;
            case R.id.btn_qrcode:
                startActivity(new Intent(this, QRCodeDemo2.class));
                break;

        }
    }

}
