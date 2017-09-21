package com.example.gank.utils;

public class UrlUtils {
	public static final String PATH="http://gank.io/api/";
	
	public static String getDayQueryUrl()
	{
		return PATH+"day/";
	}
	
	public static String getAndroidQueryUrl(int page)
	{
		return PATH+"data/Android/10/"+page;
	}
	
	public static String getIosQueryUrl(int page)
	{
		return PATH+"data/iOS/10/"+page;
	}
	
	public static String getFontDesignQueryUrl(int page)
	{
		return PATH+"data/前端/10/"+page;
	}
	
	public static String getExpandResourceUrl(int page)
	{
		return PATH+"data/拓展资源/10/"+page;
	}
	
	public static String getGoodThingQueryUrl(int page)
	{
		return PATH+"data/\u798f\u5229/10/"+page;
	}
}
