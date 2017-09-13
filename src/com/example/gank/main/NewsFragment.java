package com.example.gank.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gank.javabean.News;
import com.example.gank.utils.UrlUtil;
import com.google.gson.Gson;

import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NewsFragment extends Fragment{
	private SwipeRefreshLayout mRefreshLayout;
	private Toolbar mToolbar;
	private RecyclerView mRecyclerView;
	private News news;
	public final String TAG="NewsFragment";
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_news, container,false);
		initView(view);
		initData();
		return view;
	}
	
	public void initView(View view)
	{
		mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_news);
		LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);
		mRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refreshlayout);
		mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
				initData();
			}
		});
	}
		
	public void initData()
	{
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;
		int day=c.get(Calendar.DAY_OF_MONTH);
		getNewsByDate(year, month, day);
	}
	
	
	public void getNewsByDate(final int year,final int month,final int day)
	{
		String monthString;
		String dayString;
		if(month<10)
		{
			monthString="0"+month;
		}
		else {
			monthString=String.valueOf(month);
		}
		if(day<10)
		{
			dayString="0"+day;
		}
		else {
			dayString=String.valueOf(day);
		}
		mRefreshLayout.setRefreshing(true);
		String url=UrlUtil.getDayQueryUrl()+year+"/"+monthString+"/"+dayString;
		JsonObjectRequest request=new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						mRefreshLayout.setRefreshing(false);
						Log.d(TAG, response.toString());
						Gson gson=new Gson();
						news=gson.fromJson(response.toString(), News.class);
						if(news.getCategory().size()==0)
						{
							Calendar c=Calendar.getInstance();
							int day=c.get(Calendar.DATE);
							c.set(Calendar.DATE,day-1);
							getNewsByDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
						}
						else {
							
						}
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						mRefreshLayout.setRefreshing(false);
						Log.d(TAG, error.toString());
					}
				});
		GankApplication.getRequestQueue(getActivity()).add(request);
	}
}
