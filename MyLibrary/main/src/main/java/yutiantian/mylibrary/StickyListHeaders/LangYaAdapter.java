package yutiantian.mylibrary.StickyListHeaders;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import yutiantian.mylibrary.R;

/**
 * Created by 二更 on 2016/7/1.
 */
public class LangYaAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private LayoutInflater inflater;
    private List<LangYaSimple> mPlanDetails;

    public LangYaAdapter(Activity context, List<LangYaSimple> mPlanDetails) {
        inflater = LayoutInflater.from(context);
        this.mPlanDetails = mPlanDetails;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        if (convertView == null) {
            headerViewHolder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.activity_sticky_header_list_demo_item_headers, parent, false);
            headerViewHolder.tv_header = (TextView) convertView.findViewById(R.id.tv_head);
            convertView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }
        String headerText = this.mPlanDetails.get(position).getProject_title();
        headerViewHolder.tv_header.setText(headerText);

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(this.mPlanDetails.get(position).getProj_id());
    }

    @Override
    public int getCount() {
        return mPlanDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlanDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.ctivity_sticky_header_list_demo_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LangYaSimple item = this.mPlanDetails.get(position);
        if (item != null) {
            viewHolder.tvDate.setText(item.getTitle());
            viewHolder.tvStatus.setText(item.getDesc());
        }

        return convertView;
    }


    static class HeaderViewHolder {
        TextView tv_header;
    }

    static class ViewHolder {
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
