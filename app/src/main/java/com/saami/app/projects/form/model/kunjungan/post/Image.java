package com.saami.app.projects.form.model.kunjungan.post;

import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("raw")
	private String raw;

	@SerializedName("url")
	private String url;

	public void setRaw(String raw){
		this.raw = raw;
	}

	public String getRaw(){
		return raw;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"raw = '" + raw + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}