package com.example.gank.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gank.presenter.ActivityCollector;
import com.example.gank.utils.UrlUtil;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity{
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private SwipeRefreshLayout mRefreshLayout;
	
	public final String TAG="MainActivity";
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	public void initView()
	{
		mRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refreshlayout);
		mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT).show();
			}
		});
		mToolbar=(Toolbar)findViewById(R.id.toolbar_main);
		mToolbar.setTitle("");
		final TextView textViewTitle=(TextView)findViewById(R.id.txt_title);
		textViewTitle.setText(getResources().getString(R.string.main_title));
		setSupportActionBar(mToolbar);
		mToolbar.setNavigationIcon(R.drawable.navigation);
		mToolbar.setNavigationOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		mToolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				switch (arg0.getItemId()) {
				case R.id.btn_exit:
					ActivityCollector.finishAll();
					break;

				default:
					break;
				}
				return true;
			}
		});
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			
			@Override
			public void onDrawerStateChanged(int arg0) {
			}
			
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				float angle=arg1*360;
				ObjectAnimator animator=ObjectAnimator.ofFloat(textViewTitle, "rotationX", angle,arg1*360);
				animator.setDuration(1);
				animator.start();
			}
			
			@Override
			public void onDrawerOpened(View arg0) {
			}
			
			@Override
			public void onDrawerClosed(View arg0) {
			
			}
		});
	}
	
	public void initData()
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		Date now=new Date(System.currentTimeMillis());
		String dateString=dateFormat.format(now);
		String year=dateString.substring(0,4);
		String month=dateString.substring(4,6);
		String day=dateString.substring(6);
		getNewsByDate(year, month, day);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void getNewsByDate(String year,String month,String day)
	{
		mRefreshLayout.setRefreshing(true);
		String url=UrlUtil.getDayQueryUrl()+year+"/"+month+"/"+day;
		JsonObjectRequest request=new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						mRefreshLayout.setRefreshing(false);
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d(TAG, error.toString());
						mRefreshLayout.setRefreshing(false);
					}
				});
		GankApplication.getRequestQueue(this).add(request);
	}
}
