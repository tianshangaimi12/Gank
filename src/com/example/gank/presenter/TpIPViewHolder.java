package com.example.gank.presenter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gank.main.R;

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

