package com.example.gank.presenter;

import com.example.gank.main.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

public class TpTpITViewHolder extends ViewHolder{

	public TextView mTextView;
	public TpTpITViewHolder(View arg0) {
		super(arg0);
		mTextView=(TextView)arg0.findViewById(R.id.txt_type_txt_txt);
	}
	
}
