package cn.fragmention.demo_flow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.fragmention.R;
import cn.fragmention.demo_flow.listener.OnItemClickListener;

/**
 * 发现Discover里的子Fragment  Adapter
 * Created by YoKeyword on 16/2/1.
 */
public class FlowPagerAdapter extends RecyclerView.Adapter<FlowPagerAdapter.MyViewHolder> {
    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;

    public FlowPagerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pager, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = mItems.get(position);
        holder.tvTitle.setText(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
