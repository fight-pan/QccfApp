package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loonggg.rvbanner.lib.RecyclerViewBanner;
import com.quark.api.auto.bean.Banner;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.dfv.R;
import com.quark.dfv.adapter.FgInsuranceAdapter;
import com.quark.dfv.adapter.ModuleAdapter;
import com.quark.dfv.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.quark.dfv.api.Constants.url_1;
import static com.quark.dfv.api.Constants.url_2;
import static com.quark.dfv.api.Constants.url_3;

/**
 * Created by Administrator on 2017/5/18.
 */

public class MyDataActivity extends BaseActivity {

    @InjectView(R.id.xRylv)
    XRecyclerView xRylv;
    private FgInsuranceAdapter mAdapter;
    private ArrayList<InsuranceList> listData;
    private int refreshTime = 0;
    private int times = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydata);
        ButterKnife.inject(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRylv.setLayoutManager(layoutManager);

        xRylv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        xRylv.setLaodingMoreProgressStyle(ProgressStyle.Pacman);
        xRylv.setArrowImageView(R.drawable.loading_0);



//        View header =   LayoutInflater.from(this).inflate(R.layout.header, (ViewGroup)findViewById(android.R.id.content),false);
//        xRylv.addHeaderView(header);
        setHeader(xRylv);

        xRylv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        listData.clear();
                        InsuranceList list = new InsuranceList();
                        for(int i = 0; i < 5 ;i++){
                            list.setType("车险");
                            list.setBrand("平安出品");
                            list.setImage(url_2);
                            listData.add(list);
                        }
                        mAdapter.notifyDataSetChanged();
                        xRylv.refreshComplete();
                    }

                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        InsuranceList list = new InsuranceList();
                        for(int i = 0; i < 5 ;i++){
                            list.setType("车险");
                            list.setBrand("平安出品");
                            list.setImage(url_1);
                            listData.add(list);
                        }
                        mAdapter.notifyDataSetChanged();
                        xRylv.loadMoreComplete();
                    }
                }, 1000);

            }
        });

        listData = new ArrayList();
        mAdapter = new FgInsuranceAdapter(this,listData,handler);
        InsuranceList list = new InsuranceList();
        for(int i = 0; i < 5 ;i++){
            list.setType("车险");
            list.setBrand("平安出品");
            list.setImage(url_3);
            listData.add(list);
        }
        xRylv.setAdapter(mAdapter);
        xRylv.refresh();
    }



    private void setHeader(XRecyclerView ryView) {
        View header =   LayoutInflater.from(this).inflate(R.layout.header, (ViewGroup)findViewById(android.R.id.content),false);
        GridView gridView = (GridView) header.findViewById(R.id.GridView);
        RecyclerViewBanner viewBanner = (RecyclerViewBanner) header.findViewById(R.id.viewbanner);
        ArrayList moduleList = new ArrayList();
        ModuleAdapter mlAdapter = new ModuleAdapter(this, moduleList);
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
                Toast.makeText(MyDataActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        ryView.addHeaderView(header);
    }


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 202:

                    break;
                case 203:

                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
