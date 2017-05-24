package com.quark.dfv.mainview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quark.dfv.R;
import com.quark.dfv.base.BaseFragment;
import com.quark.dfv.ui.user.MyDataActivity;
import com.quark.dfv.ui.user.SettingActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


@SuppressLint("ResourceAsColor")
public class FragmentFour extends BaseFragment {
    View fourViewt;
    @InjectView(R.id.seven_ly)
    LinearLayout sevenLy;
    @InjectView(R.id.five_ly)
    LinearLayout fiveLy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fourViewt = inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.inject(this, fourViewt);

        fiveLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyDataActivity.class);
                startActivity(intent);
            }
        });
        sevenLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return fourViewt;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}





