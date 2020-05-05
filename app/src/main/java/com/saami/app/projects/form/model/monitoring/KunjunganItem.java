package com.saami.app.projects.form.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class KunjunganItem{
	@SerializedName("targetKunjungan")
	private int targetKunjungan;

	@SerializedName("targetRecruitment")
	private int targetRecruitment;

	public int getTargetKunjungan() {
		return targetKunjungan;
	}

	public void setTargetKunjungan(int targetKunjungan) {
		this.targetKunjungan = targetKunjungan;
	}

	public int getTargetRecruitment() {
		return targetRecruitment;
	}

	public void setTargetRecruitment(int targetRecruitment) {
		this.targetRecruitment = targetRecruitment;
	}

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