package com.quark.dfv.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.dfv.R;

import java.util.ArrayList;

/**
 * Created by pan on 2016/9/26 0026.
 * >#
 * >#
 */
public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.ViewHolder> {

    private Context context;
    private ArrayList<InsuranceList> list;
    Handler handler;
    public static final int TYPE_HEADER = 0;//设置header的状态
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }


    public InsuranceAdapter(Context context, ArrayList<InsuranceList> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new ViewHolder(mHeaderView);
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.insurance_item, null));
    }


    //点击接口
    public interface OnItemClickLitener {
        /**
         * 点击事件处理
         *
         * @author pan
         * @time 2016/10/31 0031 上午 11:18
         */
        void onItemClick(View view, int position);

        /**
         * 长按点击事件处理
         *
         * @author pan
         * @time 2016/10/31 0031 上午 11:18
         */
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        holder.brandTv.setText(list.get(pos).getBrand());
        holder.typeTv.setText(list.get(pos).getType());
        Glide.with(context)
                .load(list.get(pos).getImage())
                .dontAnimate()
                .into(holder.insuranceIv);


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    int pos = getRealPosition(holder);
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    int pos = getRealPosition(holder);
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? list.size() : list.size() + 1;

    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;

    }

    /**
     * 重写方法，在GridLayout布局时设置head独占一行
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rly;
        ImageView insuranceIv;
        TextView typeTv;
        TextView brandTv;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            rly = (RelativeLayout) itemView.findViewById(R.id.rly);
            insuranceIv = (ImageView) itemView.findViewById(R.id.insurance_iv);
            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
            brandTv = (TextView) itemView.findViewById(R.id.brand_tv);

        }
    }

    public void remove(int i) {
        list.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size() - i);
    }
}
