package yutiantian.mylibrary.baseRecycle;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import yutiantian.mylibrary.R;

/**
 * Created by Tina on 2016/9/14.
 */
public  class BaseRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<?> mLists;
    protected RecyclerView mRecyclerView;
    private OnLoadMoreListener mOnLoadMoreListener;
//    private  LayoutInflater inflater;
    private final  static int NORMAL_TYPE=1;
    private final static int LOAD_MORE_TYPE=2;
    private int mLayoutID;
    /*-----分页----*/
    // 当前页
    private int curPage = 1;
    // 每页多少行
    private int row = 20;
    //是否有下一页
    protected   boolean hasMore=false;
    protected  boolean isLoading=false;
    protected void resetPage(){
        this.curPage=1;
        this.row=20;
        hasMore=false;
    }

    protected void addPage(){
        this.curPage++;
    }
    public interface OnBingHoldViewListener{
        void bindHoldView(ViewHolder holder,int position);
    }
    protected OnBingHoldViewListener mOnBingHoldViewListener;
    public BaseRecyclerAdapter(Context context,RecyclerView recyclerView,int layoutID,List<?> list,OnBingHoldViewListener onBingHoldViewListener){
        mContext=context;
        mRecyclerView=recyclerView;
        mLists=list;
        mLayoutID=layoutID;
        mOnBingHoldViewListener=onBingHoldViewListener;
        final LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
        if(linearLayoutManager instanceof LinearLayoutManager){//线性布局下的recyclerview滚动分页
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if(/*totalItemCount<=lastVisibleItemPosition+row*/
                        recyclerView.getAdapter().getItemCount()>1&&lastVisibleItemPosition>=recyclerView.getAdapter().getItemCount()-1
                            &&hasMore&&!isLoading){
                        if(mOnLoadMoreListener!=null){
                            Toast.makeText(mContext, "loadmore"+totalItemCount+"-"+lastVisibleItemPosition, Toast.LENGTH_SHORT).show();
                            mLists.add(null);//null值为分页加载页
                            recyclerView.getAdapter().notifyDataSetChanged();
                            mOnLoadMoreListener.onLoadMoreRequest();
                            isLoading=true;
                        }
                    }
                }
            });
        }
    }
    //上拉加载到底部更新数据前,需要移除底部加载布局
    protected void finishLoadMore(){
        mLists.remove(mLists.size()-1);
        notifyDataSetChanged();
        isLoading=false;
    }
    //点击事件
    protected void setListener( final ViewHolder viewHolder) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder , position);
                }
            }
        });
        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        if(viewType==NORMAL_TYPE){
            viewHolder=ViewHolder.createViewHolder(mContext,parent,mLayoutID);
            setListener(viewHolder);
        }
        else
            viewHolder=ViewHolder.createViewHolder(mContext,parent,R.layout.layout_load_more);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder.getItemViewType()==NORMAL_TYPE){
            if(mOnBingHoldViewListener!=null)
                mOnBingHoldViewListener.bindHoldView(holder,position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mLists.get(position) != null ? NORMAL_TYPE : LOAD_MORE_TYPE;
    }


    @Override
    public int getItemCount() {
        return mLists == null ? 0 : mLists.size();
    }
    public interface  OnLoadMoreListener{
        void onLoadMoreRequest();
    }

    public void setmOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
