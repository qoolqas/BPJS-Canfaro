package com.saami.app.projects.form.model.post;

import com.google.gson.annotations.SerializedName;

public class ContactBadanUsaha{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("unitKerja")
	private String unitKerja;

	@SerializedName("phone")
	private String phone;

	@SerializedName("createdBy")
	private int createdBy;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("name")
	private String name;

	@SerializedName("badanUsahaId")
	private int badanUsahaId;

	@SerializedName("id")
	private int id;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

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

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
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

	public void setBadanUsahaId(int badanUsahaId){
		this.badanUsahaId = badanUsahaId;
	}

	public int getBadanUsahaId(){
		return badanUsahaId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"ContactBadanUsaha{" + 
			"createdAt = '" + createdAt + '\'' + 
			",unitKerja = '" + unitKerja + '\'' + 
			",phone = '" + phone + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",jabatan = '" + jabatan + '\'' + 
			",name = '" + name + '\'' + 
			",badanUsahaId = '" + badanUsahaId + '\'' + 
			",id = '" + id + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}