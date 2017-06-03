package com.quark.dfv.mainview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loonggg.rvbanner.lib.RecyclerViewBanner;
import com.quark.api.auto.bean.Banner;
import com.quark.api.auto.bean.InsuranceList;
import com.quark.api.auto.bean.ModuleList;
import com.quark.dfv.AppParam;
import com.quark.dfv.R;
import com.quark.dfv.adapter.InsuranceAdapter;
import com.quark.dfv.adapter.ModuleAdapter;
import com.quark.dfv.base.BaseFragment;
import com.quark.dfv.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static com.quark.dfv.api.Constants.url_1;
import static com.quark.dfv.api.Constants.url_2;
import static com.quark.dfv.api.Constants.url_3;


/**
 * Created by Administrator on 2017/4/24.
 */

public class FragmentOne extends BaseFragment {

    View oneview;
    ModuleAdapter mlAdapter;
    InsuranceAdapter insuranceAdapter;
    ArrayList<ModuleList> moduleList;
    ArrayList<InsuranceList> insuranceLists;
    @InjectView(R.id.ry_view)
    RecyclerView ryView;
    @InjectView(R.id.grid_swipe_refresh)
    SwipeRefreshLayout SwipeRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        oneview = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.inject(this, oneview);
//        new GlideCacheUtil().clearImageAllCache(getActivity());//清除缓存
        SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.red));
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                insuranceLists.clear();
                InsuranceList list = new InsuranceList();
                for (int i = 0; i < 5; i++) {
                    list.setType("健康险");
                    list.setBrand("国家出品");
                    list.setImage(url_2);
                    insuranceLists.add(list);
                }
                insuranceAdapter.notifyDataSetChanged();
                SwipeRefresh.setRefreshing(false);
            }
        });
        initModule();


        //计算当前时间相差
        String endTime = Utils.nowDateTime();
        if (new AppParam().getTime(getActivity()) != "0") {
            String timeCha = Utils.getTimeDifferenceHour(new AppParam().getTime(getActivity()), endTime);
            Log.e("时间差：", timeCha);
            if (Double.valueOf(timeCha) > 1) {
                String starTime = Utils.nowDateTime();
                new AppParam().setSharedPreferencesy(getActivity(), "time", starTime);
                Log.e("Tag", "超过一小时了~~~·");
            }

        } else {
            String starTime = Utils.nowDateTime();
            new AppParam().setSharedPreferencesy(getActivity(), "time", starTime);
        }
        return oneview;

    }

    private void initModule() {
        insuranceLists = new ArrayList();
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 100; i++) {
            list.setType("车险");
            list.setBrand("平安出品");
            list.setImage(url_3);
            insuranceLists.add(list);
        }

        insuranceAdapter = new InsuranceAdapter(getActivity(), insuranceLists, handler);
        ryView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setHeader(ryView);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(insuranceAdapter);
        ryView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        insuranceAdapter.setOnItemClickLitener(new InsuranceAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("保险：" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header, ryView, false);
        GridView gridView = (GridView) header.findViewById(R.id.GridView);
        RecyclerViewBanner viewBanner = (RecyclerViewBanner) header.findViewById(R.id.viewbanner);
        moduleList = new ArrayList();
        mlAdapter = new ModuleAdapter(getActivity(), moduleList);
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

//                Utils.loadImage(banners.get(position).getUrl(),bannerView);
            }
        });
        viewBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        insuranceAdapter.setHeaderView(header);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
