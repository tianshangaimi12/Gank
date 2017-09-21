package com.example.gank.presenter;

import com.example.gank.main.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TpTpIPViewHolder extends ViewHolder{
	
	public TextView mTextView;
	public ImageView mImageView;
	
	public TpTpIPViewHolder(View arg0) {
		super(arg0);
		mTextView=(TextView)arg0.findViewById(R.id.txt_type_pic_txt);
		mImageView=(ImageView)arg0.findViewById(R.id.img_type_pic_pic);
	}

}
