package com.example.swiperefreshloadlistview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

/**
 * 
 * @author zhaokaiqiang
 * 
 */
public class MainActivity extends Activity implements IXListViewListener{

	private XListView mListView;
	// 只是用来模拟异步获取数据
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (Build.VERSION.SDK_INT >= 21) {
			View decorView = getWindow().getDecorView();
			int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
			decorView.setSystemUiVisibility(option);
			getWindow().setStatusBarColor(Color.RED);   //这里可以动态设置状态栏的颜色
		}
		handler = new Handler();
		mListView = (XListView) findViewById(R.id.xListView);
		// 设置xlistview可以加载、刷新
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(true);
		// 设置回调函数
		mListView.setXListViewListener(this);
//				new IXListViewListener() {
//
//			@Override
//			public void onRefresh() {
//				// 模拟刷新数据，1s之后停止刷新
//				handler.postDelayed(new Runnable() {
//
//					@Override
//					public void run() {
//						mListView.stopRefresh();
//						Toast.makeText(MainActivity.this, "refresh",Toast.LENGTH_SHORT).show();
//					}
//				}, 1000);
//			}
//
//			@Override
//			public void onLoadMore() {
//				handler.postDelayed(new Runnable() {
//					// 模拟加载数据，1s之后停止加载
//					@Override
//					public void run() {
//						mListView.stopLoadMore();
//						Toast.makeText(MainActivity.this, "loadMore",
//								Toast.LENGTH_SHORT).show();
//					}
//				}, 1000);
//
//			}
//		});
		// 设置适配器
		mListView.setAdapter(new ListViewAdapter(this));
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		// 模拟刷新数据，1s之后停止刷新
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				mListView.stopRefresh();
				Toast.makeText(MainActivity.this, "refresh",Toast.LENGTH_SHORT).show();
			}
		}, 1000);
	}

	@Override
	public void onLoadMore() {
		handler.postDelayed(new Runnable() {
			// 模拟加载数据，1s之后停止加载
			@Override
			public void run() {
				mListView.stopLoadMore();
				Toast.makeText(MainActivity.this, "loadMore",
						Toast.LENGTH_SHORT).show();
			}
		}, 1000);

	}

}
