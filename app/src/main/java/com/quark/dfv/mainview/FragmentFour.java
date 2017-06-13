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
import com.quark.dfv.ui.user.MyActivity;
import com.quark.dfv.ui.user.MyDataActivity;
import com.quark.dfv.ui.user.RegisterActivity;
import com.quark.dfv.ui.user.SettingActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;


@SuppressLint("ResourceAsColor")
public class FragmentFour extends BaseFragment {
    View fourViewt;
    @InjectView(R.id.seven_ly)
    LinearLayout sevenLy;
    @InjectView(R.id.five_ly)
    LinearLayout fiveLy;
    @InjectView(R.id.one_ly)
    LinearLayout oneLy;
    @InjectView(R.id.profile_image)
    CircleImageView profileImage;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fourViewt = inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.inject(this, fourViewt);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        oneLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyActivity.class);
                startActivity(intent);
            }
        });

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





