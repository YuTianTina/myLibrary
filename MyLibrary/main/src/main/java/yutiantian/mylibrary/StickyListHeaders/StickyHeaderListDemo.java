package yutiantian.mylibrary.StickyListHeaders;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import yutiantian.mylibrary.R;
import yutiantian.mylibrary.refresh.SwipeRefreshLayout;

public class StickyHeaderListDemo extends AppCompatActivity {
    @Bind(R.id.list)
    StickyListHeadersListView list;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private List<LangYaSimple> mLangyaDatas;
    private LangYaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header_list_demo);
        ButterKnife.bind(this);

        mLangyaDatas = new ArrayList<LangYaSimple>();
//        initData();
        list.setOnItemClickListener(new OnPlanItemClick());
        list.setOnItemLongClickListener(new OnPlanItemLongClick());

        adapter = new LangYaAdapter(this, mLangyaDatas);
        list.setAdapter(adapter);

        int[] colors = getResources().getIntArray(R.array.progress_colors);
        refreshLayout.setColorSchemeColors(colors);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        refreshLayout.setRefreshing(false);
                    }
                },3*1000);

            }
        });
    }

    private void updateData() {

        if (adapter != null && mLangyaDatas != null) {

            adapter.notifyDataSetChanged();
        }
    }

    private class OnPlanItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LangYaSimple oLangyaSimple = (LangYaSimple) parent.getAdapter().getItem(
                    position);
            Log.e("tag", oLangyaSimple.toString());
        }
    }

    private class OnPlanItemLongClick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            LangYaSimple oLangyaSimple = mLangyaDatas.get(position);

            Log.e("tag", oLangyaSimple.toString());

            mLangyaDatas.remove(oLangyaSimple);

            updateData();

            return true;
        }
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                LangYaSimple item=new LangYaSimple(j*i+"",i+"",j*i+"",j*i+"a",""+i);
                mLangyaDatas.add(item);
            }
        }
//        mLangyaDatas.add(new LangYaSimple("1", "1", "A", "AAAA", "1"));
//        mLangyaDatas.add(new LangYaSimple("2", "1", "B", "BBB", "1"));
//        mLangyaDatas.add(new LangYaSimple("3", "1", "C", "CCC", "1"));
//        mLangyaDatas.add(new LangYaSimple("4", "1", "D", "DDD", "1"));
//        mLangyaDatas.add(new LangYaSimple("5", "1", "E", "EEE", "1"));
//
//        mLangyaDatas.add(new LangYaSimple("6", "2", "最无情", "最无情最无情最无情最无情最无情", "2"));
//        mLangyaDatas.add(new LangYaSimple("7", "2", "最无情", "最无情最无情最无情最无情最无情", "2"));
//        mLangyaDatas.add(new LangYaSimple("8", "2", "最无情", "最无情最无情最无情最无情最无情", "2"));
//        mLangyaDatas.add(new LangYaSimple("9", "2", "最无情", "最无情最无情最无情最无情最无情", "2"));
//        mLangyaDatas.add(new LangYaSimple("10", "2", "最无情", "最无情最无情最无情最无情最无情", "高手榜"));
//
//        mLangyaDatas.add(new LangYaSimple("11", "3", "情殇", "情殇情殇情殇情殇情殇情殇情殇", "美人榜"));
//        mLangyaDatas.add(new LangYaSimple("12", "3", "情殇", "情殇情殇情殇情殇情殇情殇情殇", "美人榜"));
//        mLangyaDatas.add(new LangYaSimple("13", "3", "情殇", "情殇情殇情殇情殇情殇情殇情殇", "美人榜"));
//        mLangyaDatas.add(new LangYaSimple("14", "3", "情殇", "情殇情殇情殇情殇情殇情殇情殇", "美人榜"));
//        mLangyaDatas.add(new LangYaSimple("15", "3", "情殇", "情殇情殇情殇情殇情殇情殇情殇", "美人榜"));

        adapter.notifyDataSetChanged();
    }
}
