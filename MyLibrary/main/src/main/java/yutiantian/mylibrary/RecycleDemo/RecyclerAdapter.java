package yutiantian.mylibrary.RecycleDemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yutiantian.mylibrary.R;

/**
 * Created by 二更 on 2016/7/18.
 */
public class RecyclerAdapter extends Adapter<RecyclerAdapter.MyViewHolder> {
    LayoutInflater inflater;
    Context mContext;
    List<String> data=new ArrayList<>();
    private List<Integer> mHeights;
    public RecyclerAdapter(Context context, List<String> mDatas) {
        this.mContext=context;
        this.data=mDatas;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_recycle_demo_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.id_num.setText(data.get(position));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) holder.id_num.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.id_num.setLayoutParams(lp);
        holder.id_num.setText(data.get(position));

        if(mOnItemClickListener!=null){
            holder.id_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(v,pos);
                }
            });
            holder.id_num.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.OnLongItemClick(v,pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id_num;

        public MyViewHolder(View itemView) {
            super(itemView);
            id_num= (TextView) itemView.findViewById(R.id.id_num);
        }
    }

    public  void addData(int position){
        data.add(position,"new data");
        notifyItemInserted(position);
    }
    public void deleteData(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    public interface  OnItemClickListener{
        void OnItemClick(View v,int position);
        void OnLongItemClick(View v,int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
