package com.example.gank.main;

import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gank.javabean.News;
import com.example.gank.javabean.NewsItem;
import com.example.gank.presenter.NewsRecylerViewAdapter;
import com.example.gank.utils.UrlUtils;
import com.google.gson.Gson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NewsFragment extends Fragment{
	private SwipeRefreshLayout mRefreshLayout;
	private RecyclerView mRecyclerView;
	private NewsRecylerViewAdapter adapter;
	private int nowYear;
	private int nowMonth;
	private int nowDay;
	private int totalLoadImgs;
	private int totalImgs;
	private boolean isLoadingFinish=false;
	private ImgLoadSuccessReceiver receiver;
	private IntentFilter filter;
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
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(receiver!=null)
		{
			try {
				getActivity().unregisterReceiver(receiver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mRecyclerView.removeAllViews();
	}
	
	public void initView(View view)
	{
		mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_news);
		final LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {
				super.onScrollStateChanged(recyclerView, newState);
	                    
			}
			
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if(isLoadingFinish)
				{
					int lastPosition=layoutManager.findLastVisibleItemPosition();
					/*Log.d(TAG, "visibleItemCount="+visibleItemCount);
					Log.d(TAG, "totalItemCount="+totalItemCount);
					Log.d(TAG, "firstVisbelItem="+firstVisibleItem);
					Log.d(TAG, "lastPosition="+lastPosition);*/
					if(lastPosition+1==adapter.getItemCount())
					{
						mRecyclerView.postDelayed(new Runnable() {
							public void run() {
								mRefreshLayout.setRefreshing(true);
								getBeforeNewsByDate(nowYear, nowMonth, nowDay);
							}
						}, 2000);
						isLoadingFinish=false;
					}
				}
			}
		});
		mRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refreshlayout);
		mRefreshLayout.post(new Runnable() {
			
			@Override
			public void run() {
				mRefreshLayout.setRefreshing(true);
			}
		});
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
		String url=UrlUtils.getDayQueryUrl()+year+"/"+monthString+"/"+dayString;
		JsonObjectRequest request=new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						//Log.d(TAG, response.toString());
						Gson gson=new Gson();
						final News news=gson.fromJson(response.toString(), News.class);
						if(news.getCategory().size()==0)
						{
							getBeforeNewsByDate(year, month, day);
						}
						else {
							registerReceiver();
							totalImgs=getTotalImgs(news);
							mRefreshLayout.setRefreshing(false);
							nowDay=day;
							nowMonth=month;
							nowYear=year;
							Log.d(TAG, "加载时间"+year+"---"+month+"---"+day);
							news.setDate("以下是"+year+"-"+month+"-"+day+"日推送内容");
							adapter=new NewsRecylerViewAdapter(getActivity(),news);
							adapter.setOnRecyclerViewClickListener(new RecyclerViewClickListener() {
									@Override
									public void onClick(View view, int position) {
										if(getActivity() instanceof MainActivity)
										{
											MainActivity mainActivity=(MainActivity) getActivity();
											mainActivity.loadUrl(news.getResults().getNewsItems().get(position-1).getUrl());
										}
									}
							});
							mRecyclerView.setAdapter(adapter);
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
	
	public void getBeforeNewsByDate(int year,int month,int day)
	{
		Calendar c=Calendar.getInstance();
		c.set(year, month, day);
		int theDay=c.get(Calendar.DATE);
		c.set(Calendar.DATE,theDay-1);
		Log.d(TAG, "前一天时间"+c.get(Calendar.YEAR)+"---"+(c.get(Calendar.MONTH))+"---"+c.get(Calendar.DAY_OF_MONTH));
		getNewsByDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}
	
	
	
	class ImgLoadSuccessReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d(TAG, "totalLoadImgs="+totalLoadImgs+"   totalImgs="+totalImgs+"   isLoadingFinish="+isLoadingFinish);
			totalLoadImgs++;
			if(totalLoadImgs==totalImgs)
			{
				totalLoadImgs=0;
				isLoadingFinish=true;
				if(receiver!=null)
					getActivity().unregisterReceiver(receiver);
			}
		}
		
	}
	
	public int getTotalImgs(News news)
	{
		int total=0;
		if(news!=null)
		{
			List<NewsItem> newsItems=news.getResults().getNewsItems();
			for(int i=0;i<newsItems.size();i++)
			{
				if(newsItems.get(i).getImages()!=null&&newsItems.get(i).getImages().size()>0)
				{
					total++;
				}
			}
			if(news.getResults().getGoodThings()!=null)
				total+=news.getResults().getGoodThings().size();
		}
		return total;
	}
	
	public void registerReceiver()
	{
		receiver=new ImgLoadSuccessReceiver();
		filter=new IntentFilter(NewsRecylerViewAdapter.IMG_LOAD_ACTION);
		getActivity().registerReceiver(receiver, filter);
	}
}
