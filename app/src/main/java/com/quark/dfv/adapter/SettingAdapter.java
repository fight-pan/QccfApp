package com.quark.dfv.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quark.api.auto.bean.Banner;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.api.auto.bean.ModuleList;
import com.quark.dfv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pan on 2016/9/26 0026.
 * >#
 * >#
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<InsuranceList> list;
    Handler handler;
    final List<Banner> banners = new ArrayList<>();
    ModuleAdapter mlAdapter;
    ArrayList<ModuleList> moduleList;


    public SettingAdapter(Context context, ArrayList<InsuranceList> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.setting_item, null));

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


        if (position == 0) {
            holder.homeIv.setVisibility(View.VISIBLE);
            holder.textRly.setVisibility(View.GONE);
            holder.insuranceIv.setVisibility(View.GONE);
        } else {
            holder.homeIv.setVisibility(View.GONE);
            holder.textRly.setVisibility(View.VISIBLE);
            holder.insuranceIv.setVisibility(View.VISIBLE);
            holder.brandTv.setText(list.get(position).getBrand());
            holder.typeTv.setText(list.get(position).getType());
            Glide.with(context)
                    .load(list.get(position).getImage())
                    .dontAnimate()
                    .into(holder.insuranceIv);
        }


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rly;
        ImageView insuranceIv;
        TextView typeTv;
        TextView brandTv;
        RelativeLayout textRly;
        ImageView homeIv;

        public ViewHolder(View itemView) {
            super(itemView);

            rly = (RelativeLayout) itemView.findViewById(R.id.rly);
            insuranceIv = (ImageView) itemView.findViewById(R.id.insurance_iv);
            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
            brandTv = (TextView) itemView.findViewById(R.id.brand_tv);
            textRly = (RelativeLayout) itemView.findViewById(R.id.text_rly);
            homeIv = (ImageView) itemView.findViewById(R.id.home_iv);
        }
    }

    public void remove(int i) {
        list.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size() - i);
    }
}
