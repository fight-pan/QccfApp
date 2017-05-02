package com.quark.dfv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quark.api.auto.bean.CarUserList;
import com.quark.dfv.R;

import java.util.ArrayList;

/**
 * Created by pan on 2016/7/25 0025.
 * >#
 * >#
 */
public class CarUserAdapter extends RecyclerView.Adapter<CarUserAdapter.ViewHolder> {


    private Context context;
    private ArrayList<CarUserList> list;


    public CarUserAdapter(Context context, ArrayList<CarUserList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.car_user_item, null));
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
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_default_avatar));
            holder.tv.setText("违章查询");
        }
        if (position == 1) {
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_add_public_group));
            holder.tv.setText("身份证识别");
        }
        if (position == 2) {
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_group_icon));
            holder.tv.setText("驾照识别");
        }
        if (position == 3) {
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_create_group));
            holder.tv.setText("行驶本识别");
        }

        if (position == 4) {
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_default_avatar));
            holder.tv.setText("今日油价");
        }
        if (position == 5) {
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_add_public_group));
            holder.tv.setText("今日头条");
        }
        if (position == 6) {
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.em_group_icon));
            holder.tv.setText("车机激活");
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
        return 7;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;


        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }

    public void remove(int i) {
        list.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size() - i);
    }
}
