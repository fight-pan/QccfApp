package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loonggg.rvbanner.lib.RecyclerViewBanner;
import com.quark.api.auto.bean.Banner;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.api.auto.bean.ModuleList;
import com.quark.dfv.R;
import com.quark.dfv.adapter.ModuleAdapter;
import com.quark.dfv.adapter.SettingAdapter;
import com.quark.dfv.base.BaseActivity;
import com.quark.dfv.base.BaseRecyclerAdapter;
import com.quark.dfv.ui.widget.EndLessOnScrollListener;
import com.quark.dfv.util.TLog;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.quark.dfv.api.Constants.url_1;
import static com.quark.dfv.api.Constants.url_2;
import static com.quark.dfv.api.Constants.url_3;

/**
 * Created by Administrator on 2017/4/26.
 */

public class SettingActivity extends BaseActivity {


    ArrayList<InsuranceList> datas;
    SettingAdapter adapter;
    @InjectView(R.id.ry_view)
    RecyclerView ryView;
    //    String url_1, url_2, url_3;
    @InjectView(R.id.refresh_layout)
    CircleRefreshLayout refreshLayout;
    ModuleAdapter mlAdapter;
    ArrayList<ModuleList> moduleList;

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
        for (int i = 0; i < 4; i++) {
            list.setImage("http://dfqc.iov-dfv.net/test001.jpg");
            list.setBrand("是的");
            list.setType("平安");
            datas.add(list);
        }
        adapter = new SettingAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ryView.setLayoutManager(layoutManager);
        adapter.addDatas(datas);
        ryView.setAdapter(adapter);
        /**
         * 监听addOnScrollListener这个方法，新建我们的EndLessOnScrollListener
         * 在onLoadMore方法中去完成上拉加载的操作
         * */
        ryView.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                showToast("当前的：" + position);
            }
        });
        setHeader(ryView);
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

    //每次上拉加载的时候，就加载十条数据到RecyclerView中
    private void loadMoreData(){
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 5; i++) {
            list.setImage("http://dfqc.iov-dfv.net/test003.jpg");
            list.setBrand("星星");
            list.setType("我是上拉加载出来的");
            datas.add(list);
        }
        adapter.addDatas(datas);
        adapter.notifyDataSetChanged();
    }

    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(this).inflate(R.layout.header, ryView, false);
        GridView gridView = (GridView) header.findViewById(R.id.GridView);
        RecyclerViewBanner viewBanner = (RecyclerViewBanner) header.findViewById(R.id.viewbanner);
        gridView.setFocusable(false);
        viewBanner.setFocusable(false);
        moduleList = new ArrayList();
        mlAdapter = new ModuleAdapter(this, moduleList);
        gridView.setAdapter(mlAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(position + "：当前的");
            }
        });
        final List<Banner> banners = new ArrayList<>();
        banners.add(new Banner(url_1));
        banners.add(new Banner(url_2));
        banners.add(new Banner(url_3));
        viewBanner.setRvBannerData(banners);
        viewBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, final AppCompatImageView bannerView) {
                Glide.with(bannerView.getContext())
                        .load(banners.get(position).getUrl())
                        .into(bannerView);
            }
        });
        viewBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(SettingActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setHeaderView(header);
    }

    private void addData() {
        adapter.removeDatas(datas);
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 5; i++) {
            list.setImage("http://dfqc.iov-dfv.net/test003.jpg");
            list.setBrand("星星");
            list.setType("平安保险保你全家");
            datas.add(list);
        }
        adapter.addDatas(datas);
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

    };

}
