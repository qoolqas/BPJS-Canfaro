package com.saami.app.projects.form.model.badanusaha;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BadanUsahaSearchResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("meta")
	private Meta meta;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"BadanUsahaSearchResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}