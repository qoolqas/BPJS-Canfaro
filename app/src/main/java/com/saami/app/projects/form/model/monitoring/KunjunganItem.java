package com.saami.app.projects.form.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class KunjunganItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("totalKunjungan")
	private int totalKunjungan;

	@SerializedName("totalRecruitment")
	private int totalRecruitment;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTotalKunjungan(int totalKunjungan){
		this.totalKunjungan = totalKunjungan;
	}

	public int getTotalKunjungan(){
		return totalKunjungan;
	}

	public void setTotalRecruitment(int totalRecruitment){
		this.totalRecruitment = totalRecruitment;
	}

	public int getTotalRecruitment(){
		return totalRecruitment;
	}

	@Override
 	public String toString(){
		return 
			"KunjunganItem{" + 
			"createdAt = '" + createdAt + '\'' + 
			",totalKunjungan = '" + totalKunjungan + '\'' + 
			",totalRecruitment = '" + totalRecruitment + '\'' + 
			"}";
		}
}