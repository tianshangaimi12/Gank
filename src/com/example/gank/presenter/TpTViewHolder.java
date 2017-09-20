package com.example.gank.presenter;

import com.example.gank.main.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

public class TpTViewHolder extends ViewHolder{
	public TextView mTextView;

	public TpTViewHolder(View view) {
		super(view);
		mTextView=(TextView)view.findViewById(R.id.txt_title_title);
	}
}