package com.quark.dfv.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, 2000);
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
