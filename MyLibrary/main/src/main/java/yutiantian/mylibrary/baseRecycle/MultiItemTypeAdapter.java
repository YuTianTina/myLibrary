package yutiantian.mylibrary.baseRecycle;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yutiantian.mylibrary.R;

/**
 * Created by zhy on 16/4/9.
 */
public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected RecyclerView mRecyclerView;
    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener mOnItemClickListener;
    private int totalItemCount;
    private int lastVisibleItemPosition;
    //当前滚动的position下面最小的items的临界值
    private int visibleThreshold = 5;
    /*-----分页----*/
    // 当前页
    protected int curPage = 1;
    // 每页多少行
    protected int row = 20;
    //是否有下一页
    protected  boolean hasMore=false;

    public void resetPage(){
        this.curPage=1;
        this.row=20;
        hasMore=false;
    }

    public void addPage(){
        this.curPage++;
    }

    public void isPageMore(boolean hasMore){
        this.hasMore=hasMore;
    }

    public MultiItemTypeAdapter(Context context, RecyclerView recyclerView, final List<T> datas) {
        mContext = context;
        mDatas = datas;
        mRecyclerView=recyclerView;
        mItemViewDelegateManager = new ItemViewDelegateManager();

        if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //mRecyclerView添加滑动事件监听
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if(totalItemCount<=lastVisibleItemPosition+row&&hasMore)
//                    if(isSlideToBottom(recyclerView)&&hasMore)
                    {
                        if (mMoreDataListener != null){
                            mMoreDataListener.loadMoreData();
                            hasMore=false;
                        }
                    }
                }
            });
        }
    }

    //判断recyclerview滑动到底部
    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    protected LoadMoreDataListener mMoreDataListener;
    public interface  LoadMoreDataListener{
        void loadMoreData();
    }
    //加载更多监听方法
    public void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener) {
        mMoreDataListener = onMoreDataLoadListener;
    }

    private final int LOADMORE_TYPE=99;
    @Override
    public int getItemViewType(int position) {
        if(mDatas.get(position)!=null){
            if (!useItemViewDelegateManager()) return super.getItemViewType(position);
            return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
        }else{
            return LOADMORE_TYPE;
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
        if(viewType==LOADMORE_TYPE){
            holder=ViewHolder.createViewHolder(mContext,parent,R.layout.layout_load_more);
        }else{
            ItemViewDelegate itemViewDelegate =  mItemViewDelegateManager.getItemViewDelegate(viewType);
            int layoutId = itemViewDelegate.getItemViewLayoutId();
            holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
            onViewHolderCreated(holder,holder.getConvertView());
            setListener(parent, holder, viewType);
        }
        return holder;
    }

    public void onViewHolderCreated(ViewHolder holder,View itemView){

    }

    public void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mDatas.get(position)!=null)
            convert(holder, mDatas.get(position));
        else {
//            holder.setIndeterminate(R.id.load_more_progressBar,true);
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }


    public List<T> getDatas() {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
