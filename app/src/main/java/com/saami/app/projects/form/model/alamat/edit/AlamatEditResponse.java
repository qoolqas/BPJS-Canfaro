package com.saami.app.projects.form.model.alamat.edit;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AlamatEditResponse{

	@SerializedName("data")
	private List<Integer> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("meta")
	private Meta meta;

	public void setData(List<Integer> data){
		this.data = data;
	}

	public List<Integer> getData(){
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
			"AlamatEditResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}