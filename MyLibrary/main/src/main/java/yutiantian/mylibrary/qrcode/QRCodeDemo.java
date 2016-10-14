package yutiantian.mylibrary.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yutiantian.mylibrary.R;

public class QRCodeDemo extends AppCompatActivity {

    @Bind(R.id.btn_qrcode)
    Button btnQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_demo);
        ButterKnife.bind(this);
        
    }
    private final static int REQUEST_CODE=0x00001;
    @OnClick(R.id.btn_qrcode)
    public void onClick() {
        Intent i=new Intent(this, CaptureActivity.class);
        startActivityForResult(i,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
            if(requestCode==REQUEST_CODE){
                if(data!=null){
                    Bundle bundle=data.getExtras();
                    if(bundle==null)
                        return;
                    if(bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                        String result=bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(QRCodeDemo.this, "解析结果:"+result, Toast.LENGTH_SHORT).show();
                    }else if(bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_FAILED){
                        Toast.makeText(QRCodeDemo.this, "解析二维码失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

    }
}
