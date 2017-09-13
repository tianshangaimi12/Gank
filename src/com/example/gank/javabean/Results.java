package com.example.gank.javabean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Results {
	public List<Android> getAndroids() {
		return androids;
	}
	public void setAndroids(List<Android> androids) {
		this.androids = androids;
	}
	public List<Ios> getIoss() {
		return ioss;
	}
	public void setIoss(List<Ios> ioss) {
		this.ioss = ioss;
	}
	public List<ExpandResource> getExpandResources() {
		return expandResources;
	}
	public void setExpandResources(List<ExpandResource> expandResources) {
		this.expandResources = expandResources;
	}
	public List<FontDesign> getFontDesigns() {
		return fontDesigns;
	}
	public void setFontDesigns(List<FontDesign> fontDesigns) {
		this.fontDesigns = fontDesigns;
	}
	public List<GoodThings> getGoodThings() {
		return goodThings;
	}
	public void setGoodThings(List<GoodThings> goodThings) {
		this.goodThings = goodThings;
	}
	public List<RelaxVideo> getRelaxVideos() {
		return relaxVideos;
	}
	public void setRelaxVideos(List<RelaxVideo> relaxVideos) {
		this.relaxVideos = relaxVideos;
	}
	@SerializedName("Android")
	private List<Android> androids;
	@SerializedName("iOS")
	private List<Ios> ioss;
	@SerializedName("拓展资源")
	private List<ExpandResource> expandResources;
	@SerializedName("前端")
	private List<FontDesign> fontDesigns;
	@SerializedName("福利")
	private List<GoodThings> goodThings;
	@SerializedName("休息视频")
	private List<RelaxVideo> relaxVideos;
	
	public int getNewsLength()
	{
		return androids.size()+ioss.size()+expandResources.size()+fontDesigns.size()+goodThings.size()+relaxVideos.size();
	}
	
	public boolean hasPic(int position)
	{
		List<Object> newsList=new ArrayList<Object>();
		newsList.addAll(androids);
		newsList.addAll(ioss);
		newsList.addAll(relaxVideos);
		newsList.addAll(fontDesigns);
		newsList.addAll(expandResources);
		newsList.addAll(goodThings);
		Object object = newsList.get(position);
		if (object instanceof Android) {
			return ((Android) object).hasPic();
		} else if (object instanceof Ios) {
			return ((Ios) object).hasPic();
		} else if (object instanceof ExpandResource) {
			return ((ExpandResource) object).hasPic();
		} else if (object instanceof FontDesign) {
			return ((FontDesign) object).hasPic();
		} else if (object instanceof GoodThings) {
			return ((GoodThings) object).hasPic();
		} else {
			return ((RelaxVideo) object).hasPic();
		}
	}
	
	public int getGoodThingsLocation()
	{
		return androids.size()+ioss.size()+relaxVideos.size()+fontDesigns.size()+expandResources.size();
	}
	
}
