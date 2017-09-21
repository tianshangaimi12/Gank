package com.example.gank.main;


import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gank.javabean.TypeNews;
import com.example.gank.presenter.TypeNewsRecyclerViewAdapter;
import com.example.gank.utils.UrlUtils;
import com.google.gson.Gson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TypeNewsFragment extends Fragment{
	public static final int ANDROID=1;
	public static final int IOS=2;
	public static final int FONTDESIGN=3;
	public static final int GOODTHING=4;
	private TypeNews typeNews;
	private int type=1;
	private int page=1;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerView mRecyclerView;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_query_type,container,false);
		initView(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle bundle=getArguments();
		type=bundle.getInt("type");
		queryNewsByType(page);
	}
	
	public void initView(View view)
	{
		mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiperefreshlayout_type_news);
		mSwipeRefreshLayout.post(new Runnable() {
			
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(true);
			}
		});
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				queryNewsByType(++page);
			}
		});
		mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_type_news);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
	}
	
	public void queryNewsByType(int page)
	{
		//mSwipeRefreshLayout.setRefreshing(true);
		String url="";
		switch (type) {
		case ANDROID:
			url=UrlUtils.getAndroidQueryUrl(page);
			break;
		case IOS:
			url=UrlUtils.getIosQueryUrl(page);
			break;
		case FONTDESIGN:
			url=UrlUtils.getAndroidQueryUrl(++page);
			break;
		case GOODTHING:
			url=UrlUtils.getIosQueryUrl(++page);
			break;
		default:
			break;
		}
		JsonObjectRequest request=new JsonObjectRequest(url, null, 
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("MainActivity", "TypeNewsJson="+response.toString());
						mSwipeRefreshLayout.setRefreshing(false);
						Gson gson=new Gson();
						typeNews=gson.fromJson(response.toString(), TypeNews.class);
						if(typeNews.isError()==false)
						{
							TypeNewsRecyclerViewAdapter adapter=new TypeNewsRecyclerViewAdapter(getActivity(), typeNews);
							mRecyclerView.setAdapter(adapter);
							adapter.setRecyclerViewClickListener(new RecyclerViewClickListener() {
								
								@Override
								public void onClick(View view, int position) {
									MainActivity mainActivity=(MainActivity) getActivity();
									mainActivity.loadUrl(typeNews.getResults().get(position).getUrl());
								}
							});
						}
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						mSwipeRefreshLayout.setRefreshing(false);
					}
				});
		mSwipeRefreshLayout.setRefreshing(false);
		GankApplication.getRequestQueue(getActivity()).add(request);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mRecyclerView.removeAllViews();
	}
	
}
