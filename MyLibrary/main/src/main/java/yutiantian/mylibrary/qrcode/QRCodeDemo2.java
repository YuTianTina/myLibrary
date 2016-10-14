package yutiantian.mylibrary.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import yutiantian.mylibrary.R;
import yutiantian.mylibrary.qrcode.anim.AnimUtils;

public class QRCodeDemo2 extends AppCompatActivity {

    @Bind(R.id.btn_second)
    Button btnSecond;
    @Bind(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @Bind(R.id.act_second)
    FrameLayout actSecond;
    @Bind(R.id.capture_scan_line)
    ImageView captureScanLine;
    @Bind(R.id.llscanline)
    LinearLayout llscanline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_qrcode);
        ButterKnife.bind(this);

        CaptureFragment captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                Intent resultIntent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//                bundle.putString(CodeUtils.RESULT_STRING, result);
//                resultIntent.putExtras(bundle);
//                QRCodeDemo2.this.setResult(RESULT_OK, resultIntent);
//                QRCodeDemo2.this.finish();
                Toast.makeText(QRCodeDemo2.this, result, Toast.LENGTH_SHORT).show();
                CodeUtils.isReset=true;

            }

            @Override
            public void onAnalyzeFailed() {
                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                i.putExtras(bundle);
                QRCodeDemo2.this.setResult(RESULT_OK, i);
//                QRCodeDemo2.this.finish();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        AnimUtils.scanMask(captureScanLine);


    }
}
