package com.saami.app.projects.form.model.kunjungan.post;

import com.google.gson.annotations.SerializedName;

public class KunjunganPostResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("meta")
	private Meta meta;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
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
			"KunjunganPostResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}