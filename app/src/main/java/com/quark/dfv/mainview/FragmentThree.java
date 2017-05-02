package com.quark.dfv.mainview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.quark.api.auto.bean.CarUserList;
import com.quark.dfv.R;
import com.quark.dfv.adapter.CarUserAdapter;
import com.quark.dfv.base.BaseFragment;
import com.quark.dfv.ui.widget.DividerGridItemDecoration;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("ResourceAsColor")
public class FragmentThree extends BaseFragment {


    View threeViewt;

    CarUserAdapter adapter;
    ArrayList<CarUserList> userLists;
    @InjectView(R.id.rly_view)
    RecyclerView rlyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        threeViewt = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.inject(this, threeViewt);

        getData();

        return threeViewt;
    }

    private void getData() {
        userLists = new ArrayList<>();
        rlyView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        adapter = new CarUserAdapter(getActivity(), userLists);
        rlyView.addItemDecoration(new DividerGridItemDecoration(getActivity(),3));
        rlyView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new CarUserAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0){
                    Toast.makeText(getActivity(),"违章查询",Toast.LENGTH_SHORT).show();
                }
                if (position == 1){
                    Toast.makeText(getActivity(),"身份证查询",Toast.LENGTH_SHORT).show();
                }
                if (position == 2){
                    Toast.makeText(getActivity(),"驾照识别",Toast.LENGTH_SHORT).show();
                }
                if (position == 3){
                    Toast.makeText(getActivity(),"行驶本识别",Toast.LENGTH_SHORT).show();
                }
                if (position == 4){
                    Toast.makeText(getActivity(),"今日油价",Toast.LENGTH_SHORT).show();
                }
                if (position == 5){
                    Toast.makeText(getActivity(),"今日头条",Toast.LENGTH_SHORT).show();
                }
                if (position == 6){
                    Toast.makeText(getActivity(),"车机激活",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}





