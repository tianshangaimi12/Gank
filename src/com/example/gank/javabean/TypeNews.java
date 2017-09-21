package com.example.gank.javabean;

import java.util.List;

public class TypeNews {
	private boolean error;
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public List<NewsItem> getResults() {
		return results;
	}
	public void setResults(List<NewsItem> results) {
		this.results = results;
	}
	private List<NewsItem> results;
	
}
