package com.saami.app.projects.form.model.post;

import com.google.gson.annotations.SerializedName;

public class ContactBadanUsaha{

	@SerializedName("unitKerja")
	private String unitKerja;

	@SerializedName("phone")
	private String phone;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("name")
	private String name;

	public void setUnitKerja(String unitKerja){
		this.unitKerja = unitKerja;
	}

	public String getUnitKerja(){
		return unitKerja;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setJabatan(String jabatan){
		this.jabatan = jabatan;
	}

	public String getJabatan(){
		return jabatan;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString(){
		return
				"ContactBadanUsaha{" +
						"unitKerja = '" + unitKerja + '\'' +
						",phone = '" + phone + '\'' +
						",jabatan = '" + jabatan + '\'' +
						",name = '" + name + '\'' +
						"}";
	}
}