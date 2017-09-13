package com.example.gank.presenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityCollector {
	
	public static List<Activity> mActivities=new ArrayList<Activity>();
	
	public static void addActivity(Activity activity)
	{
		mActivities.add(activity);
	}
	
	public static void removeActivity(Activity activity)
	{
		mActivities.remove(activity);
	}
	
	public static void finishAll()
	{
		for(Activity activity : mActivities)
		{
			if(!activity.isFinishing())
				activity.finish();
		}
	}
}
