package yutiantian.mylibrary.baseRecycle;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yutiantian.mylibrary.R;
import yutiantian.mylibrary.RecycleDemo.DataBean;
import yutiantian.mylibrary.RecycleDemo.DividerItemDecoration;
import yutiantian.mylibrary.refresh.SwipeRefreshLayout;

public class RecyclerBaseView extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private Handler handler=new Handler();
    List<DataBean> list=new ArrayList<>();
    BaseRecyclerAdapter mAdapter;
    StickyItemDecoration stickyItemDecoration;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_base_view);
        ButterKnife.bind(this);

        mcontext=this;
        LinearLayoutManager ll=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ll);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.divider));
        mAdapter=new BaseRecyclerAdapter(this, recyclerView, R.layout.recycleritem, list, new BaseRecyclerAdapter.OnBingHoldViewListener() {
            @Override
            public void bindHoldView(ViewHolder holder, int position) {
                holder.setText(R.id.tv_left,  "");
                holder.setText(R.id.tv_right, list.get(position).getItem() + "");
            }
        });

        stickyItemDecoration=new StickyItemDecoration(mcontext,list);
        recyclerView.addItemDecoration(stickyItemDecoration);

        recyclerView.setAdapter(mAdapter);
        mAdapter.hasMore=true;
        initData();
        mAdapter.setmOnLoadMoreListener(new BaseRecyclerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequest() {
                mAdapter.finishLoadMore();
                initData();
                stickyItemDecoration.setmList(list);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter.finishLoadMore();
//                        initData();
//                        stickyItemDecoration.setmList(list);
////                        mAdapter.notifyDataSetChanged();
////                        Toast.makeText(RecyclerBaseView.this, "load more", Toast.LENGTH_SHORT).show();
//                    }
//                },5000);
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(RecyclerBaseView.this, "position"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        int[] colors = getResources().getIntArray(R.array.progress_colors);
        refreshLayout.setColorSchemeColors(colors);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        initData();
                        mAdapter.resetPage();
//                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(RecyclerBaseView.this, "size="+list.size(), Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                },3*1000);
            }
        });
    }


    private void initData() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 20; i++) {
                DataBean dataBean = new DataBean();
                dataBean.setHeadMsg(list.size()/20+"");
                dataBean.setItem("aa" + list.size());
                list.add(dataBean);
                if(list.size()>63){
                    mAdapter.hasMore=false;
                    break;
                }

            }
        }
        mAdapter.notifyDataSetChanged();
        //tag test
        //tag
        //fdfd
    }
}
