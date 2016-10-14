package yutiantian.mylibrary.qrcode.anim;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;


/**
 * 
 * @author Cosmo Jiang
 * @email ruchao.jiang@uama.com.cn
 * @date 2014年9月10日 上午11:34:32
 * @desc 动画辅助类
 *
 */
public class AnimUtils {
	
	public static void startAlhpaAnim(View v,AnimationListener listener) {
		Animation anim = new  AlphaAnimation(0.3f,1.0f);
	      anim.setDuration(2000);
	      anim.setFillAfter(true);
	      anim.setInterpolator(new LinearInterpolator());
	      anim.setAnimationListener(listener);
	      v.startAnimation(anim);
	}
	
	
	
	public static void startAlhpaAnim(View v,long time,AnimationListener listener) {
		Animation anim = new  AlphaAnimation(0.3f,1.0f);
	      anim.setDuration(time);
	      anim.setFillAfter(true);
	      anim.setInterpolator(new LinearInterpolator());
	      anim.setAnimationListener(listener);
	      v.startAnimation(anim);
	}
	
	
	public static void endAlhpaAnim(View v,long time,AnimationListener listener) {
		  Animation anim = new  AlphaAnimation(1.0f,0.0f);
	      anim.setDuration(time);
	      anim.setFillAfter(true);
	      anim.setInterpolator(new LinearInterpolator());
	      anim.setAnimationListener(listener);
	      v.startAnimation(anim);

	}
	
	public static void startAlhpaAnim(View v) {
		Animation anim = new  AlphaAnimation(0.0f,1.0f);
	      anim.setDuration(2000);
	      anim.setFillAfter(true);
	      anim.setInterpolator(new LinearInterpolator());
	      v.startAnimation(anim);
	}
	
	
	public static void startAlhpaAnim(View v,long time) {
		Animation anim = new  AlphaAnimation(0.0f,1.0f);
	      anim.setDuration(time);
	      anim.setFillAfter(true);
	      anim.setInterpolator(new LinearInterpolator());
	      v.startAnimation(anim);
	}
	
//	public static void startController(ListView v) {
//		LayoutAnimationController cotrolller = new LayoutAnimationController(AnimationUtils.loadAnimation(v.getContext(), R.anim.gister));
//		cotrolller.setDelay(0.1f);
//	    cotrolller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//		cotrolller.setInterpolator(new LinearInterpolator());
//		v.setLayoutAnimation(cotrolller);
//	}
	
	public static void startScale(View v) {
		Animation anim = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, 
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(300);
		anim.setInterpolator(new LinearInterpolator());
		anim.setFillAfter(true);
		v.startAnimation(anim);
		
	}
	
	
	public static void progress(View v) {
		RotateAnimation anim = new RotateAnimation(0f, 361f, 1, 0.5f, 1, 0.5f);
		anim.setInterpolator(new LinearInterpolator());
		anim.setRepeatCount(-1);
		anim.setRepeatMode(RotateAnimation.RESTART);
		anim.setDuration(1400);
		v.startAnimation(anim);

	}
	
	public static void progressConverse(View v) {
		RotateAnimation anim = new RotateAnimation(0f, -361f, 1, 0.5f, 1, 0.5f);
		anim.setInterpolator(new LinearInterpolator());
		anim.setRepeatCount(-1);
		anim.setRepeatMode(RotateAnimation.RESTART);
		anim.setDuration(1400);
		v.startAnimation(anim);

	}
	
	
	public static void scanMask(View v) {
		TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f,
				TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.9f);
		mAnimation.setDuration(6000);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.RESTART);
		mAnimation.setInterpolator(new LinearInterpolator());
		v.startAnimation(mAnimation);

	}
	
	
	public static void scanMaskVertical(View v) {
		TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.9f
				, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f);
		mAnimation.setDuration(6000);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.RESTART);
		mAnimation.setInterpolator(new LinearInterpolator());
		v.startAnimation(mAnimation);
		/*TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0.9f,
				TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0f);
		mAnimation.setDuration(6000);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.RESTART);
		mAnimation.setInterpolator(new LinearInterpolator());
		v.startAnimation(mAnimation);*/

	}
	
	public static void rotateStart(View v) {
		Animation anim = new RotateAnimation(0f, -180f,
				Animation.RELATIVE_TO_SELF, 0.5F,
				Animation.RELATIVE_TO_SELF, 0.5F);
		anim.setDuration(200);
		anim.setFillAfter(true);
		anim.setFillEnabled(true);
		v.startAnimation(anim);

	}
	
	public static void rotateEnd(View v) {
		Animation anim = new RotateAnimation(-180f, 0f,
				Animation.RELATIVE_TO_SELF, 0.5F,
				Animation.RELATIVE_TO_SELF, 0.5F);
		anim.setDuration(200);
		anim.setFillAfter(true);
		anim.setFillEnabled(true);
		v.startAnimation(anim);

	}
	
	public static void rotateProgress(View v) {
		Animation anim = new RotateAnimation(0f, -180f,
				Animation.RELATIVE_TO_SELF, 0.5F,
				Animation.RELATIVE_TO_SELF, 0.5F);
		anim.setDuration(800);
		anim.setFillAfter(true);
		anim.setFillEnabled(true);
		v.startAnimation(anim);

	}
	
//
//	public static void notifyAnim(final View v) {
//		Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.notify_down);
//		anim.setFillAfter(true);
//		v.startAnimation(anim);
//
//		HandHelper.getHandler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				//v.clearAnimation();
//				Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.notify_up);
//				anim.setFillAfter(true);
//				v.startAnimation(anim);
//			}
//		}, 3000);
//
//	}

}
