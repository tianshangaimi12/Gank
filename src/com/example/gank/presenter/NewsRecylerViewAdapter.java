package com.example.gank.presenter;

import java.util.List;

import com.example.gank.javabean.News;
import com.example.gank.javabean.NewsItem;
import com.example.gank.javabean.Results;
import com.example.gank.main.R;
import com.example.gank.main.RecyclerViewClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsRecylerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
	
	private News mNews;
	private Results mResults;
	private Context mContext;
	private List<NewsItem> newsItems;
	private Intent intent;
	private RecyclerViewClickListener listener;
	public static final int TYPE_PICGIRL=1;
	public static final int TYPE_ITEM_TEXT=2;
	public static final int TYPE_ITEM_PIC=3;
	public static final int TYPE_ITEM_BOTTOM=4;
	public static final int TYPE_TITLE=5;
	public static final String IMG_LOAD_ACTION="loadingsucess";
	
	public NewsRecylerViewAdapter(Context context,News news)
	{
		mContext=context;
		mNews=news;
		mResults=mNews.getResults();
		newsItems=mResults.getNewsItems();
		intent=new Intent(IMG_LOAD_ACTION);
	}

	@Override
	public int getItemCount() {
		return newsItems.size()+1;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, final int position) {
		if(arg0 instanceof TpIPViewHolder)
		{
			NewsItem item=newsItems.get(position-1);
			TpIPViewHolder tpIPViewHolder=(TpIPViewHolder)arg0;
			tpIPViewHolder.mTextView.setText(item.getDesc());
			tpIPViewHolder.itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listener!=null)
					{
						listener.onClick(v, position);
					}
				}
			});
			Picasso.with(mContext).load(item.getImages().get(0)).into(tpIPViewHolder.mImageView,new Callback() {
				
				@Override
				public void onSuccess() {
					mContext.sendBroadcast(intent);
				}
				
				@Override
				public void onError() {
					
				}
			});
		}
		else if (arg0 instanceof TpITViewHolder) {
			NewsItem item=newsItems.get(position-1);
			TpITViewHolder tpITViewHolder=(TpITViewHolder)arg0;
			tpITViewHolder.itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listener!=null)
						listener.onClick(v,position);
				}
			});
			tpITViewHolder.mTextView.setText(item.getDesc());
		}
		else if (arg0 instanceof TpPViewHolder) {
			NewsItem item=newsItems.get(position-1);
			TpPViewHolder tpPViewHolder=(TpPViewHolder)arg0;
			tpPViewHolder.mImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listener!=null)
						listener.onClick(v, position);
				}
			});
			Picasso.with(mContext).load(item.getUrl()).into(tpPViewHolder.mImageView,new Callback() {
				
				@Override
				public void onSuccess() {
					mContext.sendBroadcast(intent);
				}
				
				@Override
				public void onError() {
					
				}
			});
		}
		else if (arg0 instanceof TpTViewHolder) {
			TpTViewHolder tpTViewHolder=(TpTViewHolder)arg0;
			tpTViewHolder.mTextView.setText(mNews.getDate());
		}
		
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int viewType) {
		ViewHolder wHolder = null;
		switch (viewType) {
		case TYPE_PICGIRL:
			View view=LayoutInflater.from(mContext).inflate(R.layout.newsrecyclerview_picgirl, arg0,false);
			TpPViewHolder tpPViewHolder=new TpPViewHolder(view);
			wHolder=tpPViewHolder;
			break;
		case TYPE_ITEM_TEXT:
			View view2=LayoutInflater.from(mContext).inflate(R.layout.newsrecyclerview_item_txt, arg0,false);
			TpITViewHolder tpITViewHolder=new TpITViewHolder(view2);
			wHolder=tpITViewHolder;
			break;
		case TYPE_ITEM_PIC:
			View view3=LayoutInflater.from(mContext).inflate(R.layout.newsrecyclerview_item_pic, arg0,false);
			TpIPViewHolder tpIPViewHolder=new TpIPViewHolder(view3);
			wHolder=tpIPViewHolder;
			break;
		case TYPE_ITEM_BOTTOM:
			break;
		case TYPE_TITLE:
			View view5=LayoutInflater.from(mContext).inflate(R.layout.newsrecyclerview_title, arg0,false);
			TpTViewHolder tpTViewHolder=new TpTViewHolder(view5);
			wHolder=tpTViewHolder;
		default:
			break;
		}
		return wHolder;
	}
	
	@Override
	public int getItemViewType(int position) {
		if(position==0)
			return TYPE_TITLE;
		else if(newsItems.get(position-1).getType().equals(mContext.getResources().getString(R.string.goodthings)))
			return TYPE_PICGIRL;
		else if (newsItems.get(position-1).getImages()!=null)
			return TYPE_ITEM_PIC;
		else return TYPE_ITEM_TEXT;

	}
	
			
	class TpPViewHolder extends ViewHolder
	{

		public TpPViewHolder(View arg0) {
			super(arg0);
			mImageView=(ImageView)arg0.findViewById(R.id.img_girl);
		}
		
		public ImageView mImageView;
		
	}
	
	class TpIBViewHolder extends ViewHolder
	{

		public TpIBViewHolder(View arg0) {
			super(arg0);
		}
		
		public TextView mTextView;
		
	}
	
	

	public void setOnRecyclerViewClickListener(RecyclerViewClickListener listener)
	{
		this.listener=listener;
	}


}
