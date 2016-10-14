package yutiantian.mylibrary.utils;

import android.content.Context;

/**
 * 基本功能：错误信息反馈信息
 */
public class ErrorMessageUtil {
    public  static String printErrorMessage(Context context,String methodName ,String errorMessage){
        return "\n############################errorMessage start ##############################\n"+MobileUtil.printMobileInfo(context)+MobileUtil.printSystemInfo()+"\n错误信息："+errorMessage+"\n方法名："+methodName+"\n当前app版本号："+VersionUtil.getVersion(context)+"\n############################errorMessage end##############################";
    }
}
