package com.example.gank.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewFragment extends Fragment{
	private WebView mWebView;
	private String url;
	private Context mContext;
	
	public WebViewFragment(Context context,String url)
	{
		this.url=url;
		this.mContext=context;
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_webview, container, false);
		final ProgressDialog dialog=ProgressDialog.show(mContext, "", "Loading...");
		mWebView=(WebView)view.findViewById(R.id.webview_news);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				if(dialog!=null&dialog.isShowing())
					dialog.dismiss();
			}
		});
		mWebView.loadUrl(url);
		return view;
	}
	
}
