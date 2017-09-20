package com.example.gank.widget;

import com.example.gank.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuItem extends RelativeLayout{

	private TextView mTextView;
	private ImageView mImageView;
	
	public MenuItem(Context context)
	{
		this(context, null);
	}
	
	public MenuItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.menu_item, this);
		mTextView=(TextView)findViewById(R.id.txt_menufragment_item);
		mImageView=(ImageView)findViewById(R.id.img_menu_img);
	}
	
	public void setText(String text)
	{
		mTextView.setText(text);
	}
	
	public void setImg(int img)
	{
		mImageView.setImageResource(img);
	}
}
