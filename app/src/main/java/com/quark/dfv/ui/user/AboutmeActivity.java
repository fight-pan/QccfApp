package com.quark.dfv.ui.user;

import android.os.Bundle;

import com.quark.dfv.R;
import com.quark.dfv.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by pan on 2016/9/9 0009.
 * >#
 * >#关于我们
 */
public class AboutmeActivity extends BaseActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutme);
        ButterKnife.inject(this);
        setTopTitle("关于我们");
        setBackButton();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
