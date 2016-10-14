package yutiantian.mylibrary.RecycleDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yutiantian.mylibrary.R;

public class RecycleDemo extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<String> mDatas;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_demo);
        ButterKnife.bind(this);
        initData();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new RecyclerAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mAdapter.setmOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Toast.makeText(RecycleDemo.this, mDatas.get(position), Toast.LENGTH_SHORT).show();
                mAdapter.deleteData(position);
            }

            @Override
            public void OnLongItemClick(View v, int position) {

            }
        });

    }


    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add((char) i + "");
        }
    }
}
