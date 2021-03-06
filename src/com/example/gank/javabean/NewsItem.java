package com.example.gank.javabean;

import java.util.List;

public class NewsItem {
	private String _idString;
	private String createdAt;
	private String desc;
	private String publishedAt;
	private String type;
	private String url;
	private boolean used;
	private String who;
	private List<String> images;
	
	public List<String> getImages()
	{
		return images;
	}
	
	public void setImages(List<String> images)
	{
		this.images=images;
	}
	public String get_idString() {
		return _idString;
	}
	public void set_idString(String _idString) {
		this._idString = _idString;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	
	public boolean hasPic()
	{
		if (images.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
}
