package com.quark.dfv.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loonggg.rvbanner.lib.RecyclerViewBanner;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.api.auto.bean.ModuleList;
import com.quark.dfv.R;
import com.quark.dfv.adapter.ModuleAdapter;
import com.quark.dfv.adapter.MyAdapter;
import com.quark.dfv.base.BaseActivity;
import com.quark.dfv.ui.smileyloadingview.CustomHeader;
import com.youth.banner.loader.ImageLoader;

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


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.XRefreshView)
    com.andview.refreshview.XRefreshView XRefreshView;
    private MyAdapter mAdapter;
    private ArrayList<InsuranceList> listData;
    private int refreshTime = 0;
    private int times = 0;

    private int mLoadCount = 0;
    private boolean isList = true;//false 为grid布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydata);
        ButterKnife.inject(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);

        listData = new ArrayList();
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 1; i++) {
            list.setType("车险");
            list.setBrand("平安出品");
            list.setImage(url_2);
            listData.add(list);
        }
        mAdapter = new MyAdapter(listData, this);
//        setHeader(recyclerview);
        setHeader();
        recyclerview.setAdapter(mAdapter);
        XRefreshView.setPullLoadEnable(true);
        XRefreshView.setAutoRefresh(true);
        XRefreshView.setPinnedTime(1000);
//        XRefreshView.setCustomHeaderView(new SmileyHeaderView(this));
        XRefreshView.setCustomHeaderView(new CustomHeader(this,1000));

        XRefreshView.setXRefreshViewListener(new com.andview.refreshview.XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        XRefreshView.stopRefresh();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        InsuranceList list = new InsuranceList();
                        for (int i = 0; i < 5; i++) {
                            list.setType("上拉加载的");
                            list.setBrand("平安出品");
                            list.setImage(url_1);
                            listData.add(list);
                        }
                        mAdapter.notifyDataSetChanged();
                        XRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });

    }

    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(this).inflate(R.layout.header, ryView, false);
        GridView gridView = (GridView) header.findViewById(R.id.GridView);
        RecyclerViewBanner viewBanner = (RecyclerViewBanner) header.findViewById(R.id.viewbanner);
       ArrayList<ModuleList> moduleList = new ArrayList();
       ModuleAdapter mlAdapter = new ModuleAdapter(this, moduleList);
        gridView.setAdapter(mlAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(position + "：当前的");
            }
        });
        final List<com.quark.api.auto.bean.Banner> banners = new ArrayList<>();
        banners.add(new com.quark.api.auto.bean.Banner(url_1));
        banners.add(new com.quark.api.auto.bean.Banner(url_2));
        banners.add(new com.quark.api.auto.bean.Banner(url_3));
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
        mAdapter.setHeaderView(header);
    }


    private void setHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.headerone, (ViewGroup) findViewById(android.R.id.content), false);
        GridView gridView = (GridView) header.findViewById(R.id.GridView);
        com.youth.banner.Banner banner = (com.youth.banner.Banner) header.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        Image[] images =
        List<com.quark.api.auto.bean.Banner> banners = new ArrayList<>();
        banners.add(new com.quark.api.auto.bean.Banner(url_1));
        banners.add(new com.quark.api.auto.bean.Banner(url_2));
        banners.add(new com.quark.api.auto.bean.Banner(url_3));
        banner.setImages(banners);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        mAdapter.setHeaderView(header);
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
//            eg：
            //Glide 加载图片简单用法
            Glide.with(context).load(path.toString()).into(imageView);

        }


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
