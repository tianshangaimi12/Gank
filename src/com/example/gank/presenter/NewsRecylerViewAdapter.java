package com.example.gank.presenter;

import java.util.zip.Inflater;

import com.example.gank.javabean.News;
import com.example.gank.main.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsRecylerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
	
	private News mNews;
	private Context mContext;
	public static final int TYPE_PICGIRL=1;
	public static final int TYPE_ITEM_TEXT=2;
	public static final int TYPE_ITEM_PIC=3;
	public static final int TYPE_ITEM_BOTTOM=4;
	
	public NewsRecylerViewAdapter(Context context,News news)
	{
		mContext=context;
		mNews=news;
	}

	@Override
	public int getItemCount() {
		return mNews.getResults().getNewsLength()+1;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int position) {
		if(arg0 instanceof TpIPViewHolder)
		{
			TpIPViewHolder tpIPViewHolder=(TpIPViewHolder)arg0;
			
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
		default:
			break;
		}
		return wHolder;
	}
	
	@Override
	public int getItemViewType(int position) {
		if(position==mNews.getResults().getGoodThingsLocation())
			return TYPE_PICGIRL;
		else if (position==getItemCount()-1) {
			return TYPE_ITEM_BOTTOM;
		}
		else if (mNews.getResults().hasPic(position)) {
			return TYPE_ITEM_PIC;
		}
		else return TYPE_ITEM_TEXT;
	}
	
	class TpIPViewHolder extends ViewHolder
	{
		public TpIPViewHolder(View arg0) {
			super(arg0);
			mTextView=(TextView)arg0.findViewById(R.id.txt_pic_txt);
			mImageView=(ImageView)arg0.findViewById(R.id.img_pic_pic);
		}
		public TextView mTextView;
		public ImageView mImageView;
	}
	
	class TpITViewHolder extends ViewHolder
	{

		public TpITViewHolder(View arg0) {
			super(arg0);
			mTextView=(TextView)arg0.findViewById(R.id.txt_txt_txt);
		}
		
		public TextView mTextView;
		
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

}
