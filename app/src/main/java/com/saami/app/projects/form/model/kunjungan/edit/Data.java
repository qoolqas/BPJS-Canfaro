package com.saami.app.projects.form.model.kunjungan.edit;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("kunjungan")
	private List<Integer> kunjungan;

	@SerializedName("contactBadanUsaha")
	private List<Integer> contactBadanUsaha;

	@SerializedName("badanUsaha")
	private List<Integer> badanUsaha;

	public void setKunjungan(List<Integer> kunjungan){
		this.kunjungan = kunjungan;
	}

	public List<Integer> getKunjungan(){
		return kunjungan;
	}

	public void setContactBadanUsaha(List<Integer> contactBadanUsaha){
		this.contactBadanUsaha = contactBadanUsaha;
	}

	public List<Integer> getContactBadanUsaha(){
		return contactBadanUsaha;
	}

	public void setBadanUsaha(List<Integer> badanUsaha){
		this.badanUsaha = badanUsaha;
	}

	public List<Integer> getBadanUsaha(){
		return badanUsaha;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"kunjungan = '" + kunjungan + '\'' + 
			",contactBadanUsaha = '" + contactBadanUsaha + '\'' + 
			",badanUsaha = '" + badanUsaha + '\'' + 
			"}";
		}
}