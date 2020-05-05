package com.saami.app.projects.form.model.kunjungan.delete;

import com.google.gson.annotations.SerializedName;

public class KunjunganDeleteResponse{

	@SerializedName("data")
	private int data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("meta")
	private Meta meta;

	public void setData(int data){
		this.data = data;
	}

	public int getData(){
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
			"KunjunganDeleteResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}