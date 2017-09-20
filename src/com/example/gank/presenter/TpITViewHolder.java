package com.example.gank.presenter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.example.gank.main.R;

class TpITViewHolder extends ViewHolder
{

	public TpITViewHolder(View arg0) {
		super(arg0);
		mTextView=(TextView)arg0.findViewById(R.id.txt_txt_txt);
	}
	
	public TextView mTextView;

}
