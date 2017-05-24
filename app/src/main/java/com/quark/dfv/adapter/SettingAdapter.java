package com.quark.dfv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.dfv.R;
import com.quark.dfv.base.BaseRecyclerAdapter;

/**
 * Created by pan on 2016/9/26 0026.
 * >#
 * >#
 */
public class SettingAdapter extends BaseRecyclerAdapter<InsuranceList> {

    Context context;

    public SettingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {

        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_item, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, InsuranceList data) {

        if(viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).typeTv.setText(data.getType());
            ((MyHolder) viewHolder).branTv.setText(data.getBrand());
            Glide.with(context)
                    .load(data.getImage())
                    .dontAnimate()
                    .into(((MyHolder) viewHolder).insuranceIv);
        }

    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView insuranceIv;
        TextView typeTv;
        TextView branTv;

        public MyHolder(View itemView) {
            super(itemView);

            insuranceIv = (ImageView) itemView.findViewById(R.id.insurance_iv);
            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
            branTv = (TextView) itemView.findViewById(R.id.brand_tv);

        }
    }
}
