package com.saami.app.projects.form.model.monitoring;

import com.google.gson.annotations.SerializedName;

public class CurrentTarget{

	@SerializedName("targetKunjungan")
	private int targetKunjungan;

	@SerializedName("targetRecruitment")
	private int targetRecruitment;

	public void setTargetKunjungan(int targetKunjungan){
		this.targetKunjungan = targetKunjungan;
	}

	public int getTargetKunjungan(){
		return targetKunjungan;
	}

	public void setTargetRecruitment(int targetRecruitment){
		this.targetRecruitment = targetRecruitment;
	}

	public int getTargetRecruitment(){
		return targetRecruitment;
	}

	@Override
 	public String toString(){
		return 
			"CurrentTarget{" + 
			"targetKunjungan = '" + targetKunjungan + '\'' + 
			",targetRecruitment = '" + targetRecruitment + '\'' + 
			"}";
		}
}