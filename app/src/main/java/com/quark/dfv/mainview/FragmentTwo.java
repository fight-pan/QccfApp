package com.quark.dfv.mainview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quark.api.auto.bean.InsuranceList;
import com.quark.dfv.R;
import com.quark.dfv.adapter.FgInsuranceAdapter;
import com.quark.dfv.base.BaseFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FragmentTwo extends BaseFragment {
    View twoLayout;


    FgInsuranceAdapter adapter;
    ArrayList<InsuranceList> insuranceLists;
    @InjectView(R.id.ry_view)
    RecyclerView ryView;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        twoLayout = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.inject(this, twoLayout);

        getData();

        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(),R.color.red));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                swipeRefresh.setEnabled(false);
                insuranceLists.clear();
                InsuranceList list = new InsuranceList();
                for (int i = 0; i < 6; i++) {
                    list.setType("呵护");
                    list.setBrand("保驾护航");
                    list.setImage("http://dfqc.iov-dfv.net/test002.jpg");
                    insuranceLists.add(list);
                }
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });

        return twoLayout;
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
}




