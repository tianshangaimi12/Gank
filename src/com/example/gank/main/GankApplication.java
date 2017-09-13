package com.example.gank.main;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;

public class GankApplication extends Application{
	
	private static RequestQueue queue;
	
	public static RequestQueue getRequestQueue(Context context)
	{
		if(queue==null)
			queue=Volley.newRequestQueue(context);
		return queue;
	}

}
