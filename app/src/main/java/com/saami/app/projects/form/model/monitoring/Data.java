package com.saami.app.projects.form.model.monitoring;

import java.util.List;
import com.google.gson.annotations.SerializedName;

	public class Data{

	@SerializedName("kunjungan")
	private List<KunjunganItem> kunjungan;

	@SerializedName("currentTarget")
	private CurrentTarget currentTarget;

	public void setKunjungan(List<KunjunganItem> kunjungan){
		this.kunjungan = kunjungan;
	}

	public List<KunjunganItem> getKunjungan(){
		return kunjungan;
	}

	public void setCurrentTarget(CurrentTarget currentTarget){
		this.currentTarget = currentTarget;
	}

	public CurrentTarget getCurrentTarget(){
		return currentTarget;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"kunjungan = '" + kunjungan + '\'' + 
			",currentTarget = '" + currentTarget + '\'' + 
			"}";
		}
}