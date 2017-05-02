package com.quark.dfv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.ModuleList;
import com.quark.dfv.R;

import java.util.List;

/**
 * Created by pan on 2016/7/25 0025.
 * >#
 * >#
 */
public class ModuleAdapter extends BaseAdapter {

    private Context context;
    private List<ModuleList> list;

    public ModuleAdapter(Context context, List<ModuleList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.module_item, null);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);

        if (position == 0){
            iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_default_avatar));
            tv.setText("车机激活");
        }
        if (position == 1){
            iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_add_public_group));
            tv.setText("流量购买");
        }
        if (position == 2){
            iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_group_icon));
            tv.setText("绑定微信");
        }
        if (position == 3){
            iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_create_group));
            tv.setText("更多");
        }
        return convertView;
    }


}
