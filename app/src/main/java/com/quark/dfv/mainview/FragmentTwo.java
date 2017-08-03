package com.quark.dfv.mainview;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.quark.dfv.R;
import com.quark.dfv.adapter.FgInsuranceAdapter;
import com.quark.dfv.adapter.ModuleAdapter;
import com.quark.dfv.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.quark.dfv.api.Constants.url_1;
import static com.quark.dfv.api.Constants.url_2;
import static com.quark.dfv.api.Constants.url_3;

public class FragmentTwo extends BaseFragment {
    View twoLayout;
    ModuleAdapter mlAdapter;
    ArrayList<ModuleList> moduleList;
    FgInsuranceAdapter adapter;
    ArrayList<InsuranceList> insuranceLists;
    @InjectView(R.id.ry_view)
    RecyclerView ryView;
    @InjectView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        twoLayout = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.inject(this, twoLayout);

        getData();

//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                swipeRefresh.setEnabled(false);
//                insuranceLists.clear();
//                InsuranceList list = new InsuranceList();
//                for (int i = 0; i < 6; i++) {
//                    list.setType("呵护");
//                    list.setBrand("保驾护航");
//                    list.setImage("http://dfqc.iov-dfv.net/test002.jpg");
//                    insuranceLists.add(list);
//                }
//                adapter.notifyDataSetChanged();
//                swipeRefresh.setRefreshing(false);
//            }
//        });

        setHeadler();

        setThemeColor(R.color.red, R.color.red);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                insuranceLists.clear();
                InsuranceList list = new InsuranceList();
                for (int i = 0; i < 6; i++) {
                    list.setType("呵护");
                    list.setBrand("保驾护航");
                    list.setImage("http://dfqc.iov-dfv.net/test002.jpg");
                    insuranceLists.add(list);
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                InsuranceList list = new InsuranceList();
                for (int i = 0; i < 6; i++) {
                    list.setType("哈哈哈哈哈");
                    list.setBrand("啦啦啦啦啦啦");
                    list.setImage("http://dfqc.iov-dfv.net/test001.jpg");
                    insuranceLists.add(list);
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore(2000);

            }
        });

        return twoLayout;
    }

    private void setThemeColor(int colorPrimary, int colorPrimaryDark) {
        refreshLayout.setPrimaryColorsId(colorPrimary, android.R.color.white);
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), colorPrimaryDark));
        }
//        refreshLayout.setRefreshHeader()
    }

    private void getData() {
        insuranceLists = new ArrayList<>();
        InsuranceList list = new InsuranceList();
        for (int i = 0; i < 9; i++) {
            list.setType("呵护健康");
            list.setBrand("为我的健康保驾护航");
            list.setImage("http://dfqc.iov-dfv.net/test003.jpg");
            insuranceLists.add(list);
        }
        ryView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FgInsuranceAdapter(getActivity(), insuranceLists, handler);
        ryView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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


    private void setHeadler(){
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
    }
}




