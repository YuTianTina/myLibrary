package yutiantian.mylibrary.RecycleDemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yutiantian.mylibrary.R;
import yutiantian.mylibrary.refresh.SwipeRefreshLayout;

public class RecyclerBaseView extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    List<DataBean> list=new ArrayList<>();;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    CommonAdapter mAdapter;
    LoadMoreWrapper loadMoreWrapper;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_base_view);
        ButterKnife.bind(this);
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommonAdapter<DataBean>(this, R.layout.recycleritem, list) {
            @Override
            protected void convert(ViewHolder holder, DataBean dataBean, int position) {
//                holder.setText(R.id.tv_left, dataBean.getHeadId() + "");
                holder.setText(R.id.tv_right, dataBean.getItem() + "");
            }
        };
//        recyclerView.setAdapter(mAdapter);
//        mHeaderAndFooterWrapper=new HeaderAndFooterWrapper(mAdapter);
        View footview = LayoutInflater.from(this).inflate(R.layout.layout_foot_view, null);
//        mHeaderAndFooterWrapper.addFootView(footview);
//        recyclerView.setAdapter(mHeaderAndFooterWrapper);
//        mHeaderAndFooterWrapper.notifyDataSetChanged();



        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        loadMoreWrapper.setLoadMoreView(footview);

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(RecyclerBaseView.this, "this item is" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(loadMoreWrapper);
        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData();
//                loadMoreWrapper.notifyDataSetChanged();

                Toast.makeText(RecyclerBaseView.this, "load more", Toast.LENGTH_SHORT).show();
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
                        initData();
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(RecyclerBaseView.this, "size="+list.size(), Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                },3*1000);
            }
        });

/*
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Toast.makeText(RecyclerBaseView.this, "onScrollStateChanged"+newState, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isSlideToBottom(recyclerView)){

                    Toast.makeText(RecyclerBaseView.this, "onScrolled"+isSlideToBottom(recyclerView), Toast.LENGTH_SHORT).show();
                    initData();
                    mAdapter.notifyDataSetChanged();

                }


            }
        });*/
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }


    private void initData() {
        for (int i = 0; i < 20; i++) {
            DataBean dataBean = new DataBean();
//            dataBean.setHeadId(i);
            dataBean.setItem("a" + i);
            list.add(dataBean);
        }
    }
}
