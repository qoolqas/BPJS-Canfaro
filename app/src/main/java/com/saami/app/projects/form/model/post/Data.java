package com.saami.app.projects.form.model.post;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("kunjungan")
	private Kunjungan kunjungan;

	@SerializedName("contactBadanUsaha")
	private ContactBadanUsaha contactBadanUsaha;

	@SerializedName("badanUsaha")
	private BadanUsaha badanUsaha;

	public void setKunjungan(Kunjungan kunjungan){
		this.kunjungan = kunjungan;
	}

	public Kunjungan getKunjungan(){
		return kunjungan;
	}

	public void setContactBadanUsaha(ContactBadanUsaha contactBadanUsaha){
		this.contactBadanUsaha = contactBadanUsaha;
	}

	public ContactBadanUsaha getContactBadanUsaha(){
		return contactBadanUsaha;
	}

	public void setBadanUsaha(BadanUsaha badanUsaha){
		this.badanUsaha = badanUsaha;
	}

	public BadanUsaha getBadanUsaha(){
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