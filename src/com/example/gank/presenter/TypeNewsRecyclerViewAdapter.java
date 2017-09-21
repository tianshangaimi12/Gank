package com.example.gank.presenter;

import java.util.List;

import com.example.gank.javabean.NewsItem;
import com.example.gank.javabean.TypeNews;
import com.example.gank.main.R;
import com.example.gank.main.RecyclerViewClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class TypeNewsRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
	private List<NewsItem> results;
	private Context context;
	private RecyclerViewClickListener recyclerViewClickListener;
	
	public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener)
	{
		this.recyclerViewClickListener=recyclerViewClickListener;
	}
	public TypeNewsRecyclerViewAdapter(Context context,TypeNews typeNews)
	{
		this.context=context;
		results=typeNews.getResults();
	}
	
	@Override
	public int getItemCount() {
		return results.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, final int arg1) {
		if(arg0 instanceof TpTpITViewHolder)
		{
			TpTpITViewHolder tpITViewHolder=(TpTpITViewHolder)arg0;
			tpITViewHolder.mTextView.setText(results.get(arg1).getDesc());
			tpITViewHolder.itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(recyclerViewClickListener!=null)
						recyclerViewClickListener.onClick(v, arg1);
				}
			});
		}
		
		if(arg0 instanceof TpTpIPViewHolder)
		{
			TpTpIPViewHolder tpIPViewHolder=(TpTpIPViewHolder)arg0;
			tpIPViewHolder.mTextView.setText(results.get(arg1).getDesc());
			Picasso.with(context).load(results.get(arg1).getImages().get(0)).into(tpIPViewHolder.mImageView,
					new Callback() {
						
						@Override
						public void onSuccess() {
						}
						
						@Override
						public void onError() {
						}
					});
			tpIPViewHolder.itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(recyclerViewClickListener!=null)
						recyclerViewClickListener.onClick(v, arg1);
				}
			});
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		if(arg1==NewsRecylerViewAdapter.TYPE_ITEM_TEXT)
		{
			View view=LayoutInflater.from(context).inflate(R.layout.typenewsrecyclerview_item_txt, arg0,false);
			TpTpITViewHolder tpITViewHolder=new TpTpITViewHolder(view);
			return tpITViewHolder;
		}
		if(arg1==NewsRecylerViewAdapter.TYPE_ITEM_PIC)
		{
			View view=LayoutInflater.from(context).inflate(R.layout.typenewsrecyclerview_item_pic, arg0,false);
			TpTpIPViewHolder tpIPViewHolder=new TpTpIPViewHolder(view);
			return tpIPViewHolder;
		}
		return null;
	}
	
	@Override
	public int getItemViewType(int position) {
		if(results.get(position).getImages()==null)
			return NewsRecylerViewAdapter.TYPE_ITEM_TEXT;
		else {
			return NewsRecylerViewAdapter.TYPE_ITEM_PIC;
		}
	}
	
	
}
