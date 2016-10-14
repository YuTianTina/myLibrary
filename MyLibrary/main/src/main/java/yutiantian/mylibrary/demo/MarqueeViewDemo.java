package yutiantian.mylibrary.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yutiantian.mylibrary.R;
import yutiantian.mylibrary.customview.MarqueeView;
import yutiantian.mylibrary.utils.ToastUtil;

public class MarqueeViewDemo extends AppCompatActivity {

    @Bind(R.id.marqueeView)
    MarqueeView marqueeView;
    @Bind(R.id.iv_loudspeaker4)
    ImageView ivLoudspeaker4;
    @Bind(R.id.marqueeView4)
    MarqueeView marqueeView4;
    @Bind(R.id.iv_loudspeaker3)
    ImageView ivLoudspeaker3;
    @Bind(R.id.marqueeView3)
    MarqueeView marqueeView3;
    @Bind(R.id.iv_loudspeaker2)
    ImageView ivLoudspeaker2;
    @Bind(R.id.marqueeView2)
    MarqueeView marqueeView2;
    @Bind(R.id.iv_loudspeaker1)
    ImageView ivLoudspeaker1;
    @Bind(R.id.marqueeView1)
    MarqueeView marqueeView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_view_demo);
        ButterKnife.bind(this);
        final Context mcontext= this;
        final List<String> list=new ArrayList<String>();
        StringBuffer sb=new StringBuffer();
        for(int i = 0;i<6;i++){
            String str="ytt"+i+";";
            list.add(str);
            sb.append(str);
        }
        marqueeView.startWithList(list);
        marqueeView1.startWithText(sb.toString());
        marqueeView2.startWithText(sb.toString());
        marqueeView3.startWithText(sb.toString());
        marqueeView4.startWithText(sb.toString());

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                ToastUtil.show(mcontext,list.get(position));
            }
        });


    }
}
