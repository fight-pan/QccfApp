package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.quark.api.auto.bean.InsuranceList;
import com.quark.dfv.R;
import com.quark.dfv.adapter.SettingAdapter;
import com.quark.dfv.base.BaseActivity;
import com.quark.dfv.util.TLog;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/4/26.
 */

public class SettingActivity extends BaseActivity {


    ArrayList<InsuranceList> datas;
    SettingAdapter adapter;
    @InjectView(R.id.ry_view)
    RecyclerView ryView;
    String url_1, url_2, url_3;
    @InjectView(R.id.refresh_layout)
    CircleRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);

        getData();

    }

    private void getData() {
        datas = new ArrayList<>();
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 20; i++) {
            list.setImage("http://dfqc.iov-dfv.net/test001.jpg");
            list.setBrand("是的");
            list.setType("平安");
            datas.add(list);
        }
        adapter = new SettingAdapter(this, datas, handler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ryView.setLayoutManager(layoutManager);
        ryView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new SettingAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SettingActivity.this, "点击", Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        refreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        // do something when refresh starts
                        TLog.error("刷新时做的事情");
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                //显示dialog
                                addData();

                            }
                        }, 2000);
                    }

                    @Override
                    public void completeRefresh() {
                        // do something when refresh complete
                        TLog.error("刷新完成后做的事情");
//                        refreshLayout.finishRefreshing();
                    }
                });
    }

    private void addData() {
        datas.clear();
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 8; i++) {
            list.setImage("http://dfqc.iov-dfv.net/test003.jpg");
            list.setBrand("星星");
            list.setType("平安保险保你全家");
            datas.add(list);
        }
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 202:
//                    current = msg.arg1;
////                    ImageUtils.showSheetPic(CameraActivity.this, handlerphoto);
//                    startTakePhotoByPermissions();
                    break;
                case 203:
//                    current = msg.arg1;
//                    pics.remove(current);
//                    mSelectPath.remove(current);
//                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

}
