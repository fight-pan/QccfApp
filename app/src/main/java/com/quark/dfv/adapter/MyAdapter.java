package com.quark.dfv.adapter;

import android.content.Context;
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
 * Created by jianghejie on 15/11/26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<InsuranceList> datas;
    public Context context;
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

    public MyAdapter(ArrayList<InsuranceList> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public MyAdapter(ArrayList<InsuranceList> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fg_insurance_item, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(viewHolder);
        viewHolder.brandTv.setText(datas.get(pos).getBrand());
        viewHolder.typeTv.setText(datas.get(pos).getType());
        Glide.with(context)
                .load(datas.get(pos).getImage())
                .dontAnimate()
                .into(viewHolder.insuranceIv);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mHeaderView == null ? datas.size() : datas.size() + 1;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rly;
        ImageView insuranceIv;
        TextView typeTv;
        TextView brandTv;

        public ViewHolder(View view) {
            super(view);
            if (view == mHeaderView) return;
            rly = (RelativeLayout) itemView.findViewById(R.id.rly);
            insuranceIv = (ImageView) itemView.findViewById(R.id.insurance_iv);
            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
            brandTv = (TextView) itemView.findViewById(R.id.brand_tv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;

    }
}
