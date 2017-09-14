package com.example.gank.javabean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Results {
	@SerializedName("Android")
	private List<NewsItem> androids;
	public List<NewsItem> getAndroids() {
		return androids;
	}

	public void setAndroids(List<NewsItem> androids) {
		this.androids = androids;
	}

	public List<NewsItem> getIoss() {
		return ioss;
	}

	public void setIoss(List<NewsItem> ioss) {
		this.ioss = ioss;
	}

	public List<NewsItem> getExpandResources() {
		return expandResources;
	}

	public void setExpandResources(List<NewsItem> expandResources) {
		this.expandResources = expandResources;
	}

	public List<NewsItem> getFontDesigns() {
		return fontDesigns;
	}

	public void setFontDesigns(List<NewsItem> fontDesigns) {
		this.fontDesigns = fontDesigns;
	}

	public List<NewsItem> getGoodThings() {
		return goodThings;
	}

	public void setGoodThings(List<NewsItem> goodThings) {
		this.goodThings = goodThings;
	}

	public List<NewsItem> getRelaxVideos() {
		return relaxVideos;
	}

	public void setRelaxVideos(List<NewsItem> relaxVideos) {
		this.relaxVideos = relaxVideos;
	}

	@SerializedName("iOS")
	private List<NewsItem> ioss;
	@SerializedName("拓展资源")
	private List<NewsItem> expandResources;
	@SerializedName("前端")
	private List<NewsItem> fontDesigns;
	@SerializedName("福利")
	private List<NewsItem> goodThings;
	@SerializedName("休息视频")
	private List<NewsItem> relaxVideos;
	
	public List<NewsItem> getNewsItems()
	{
		List<NewsItem> newsList=new ArrayList<NewsItem>();
		if(androids!=null)
		newsList.addAll(androids);
		if(ioss!=null)
		newsList.addAll(ioss);
		if(relaxVideos!=null)
		newsList.addAll(relaxVideos);
		if(fontDesigns!=null)
		newsList.addAll(fontDesigns);
		if(expandResources!=null)
		newsList.addAll(expandResources);
		if(goodThings!=null)
		newsList.addAll(goodThings);
		return newsList;
	}
	
}
