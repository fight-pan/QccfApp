package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.os.Handler;

import com.quark.dfv.R;
import com.quark.dfv.base.BaseActivity;
import com.quark.dfv.mainview.MainActivity;

/**
 * Created by Administrator on 2017/4/28.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        },2000);
    }

    private void next() {
        startActivityByClass(MainActivity.class);
        finish();
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
