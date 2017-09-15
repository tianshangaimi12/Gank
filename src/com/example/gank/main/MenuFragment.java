package com.example.gank.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MenuFragment extends Fragment{
	private TextView mTextViewName;
	private Button mButtonExit;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_menu, container,false);
		initView(view);
		return view;
	}
	
	public void initView(View view)
	{
		mTextViewName=(TextView)view.findViewById(R.id.txt_menufragment_name);
		Intent intent=getActivity().getIntent();
		String name=intent.getStringExtra("name");
		if(!TextUtils.isEmpty(name))
		{
			mTextViewName.setText(name);
		}
		else {
			mTextViewName.setText(getResources().getString(R.string.noname_txt));
		}
		mButtonExit=(Button)view.findViewById(R.id.btn_return_login);
		mButtonExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
	}
}
