package com.saami.app.projects.form.model.alamat;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("kota")
	private String kota;

	@SerializedName("namaBadanUsaha")
	private String namaBadanUsaha;

	@SerializedName("createdBy")
	private int createdBy;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("badanUsahaId")
	private Object badanUsahaId;

	@SerializedName("id")
	private int id;

	@SerializedName("alamat")
	private String alamat;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setNamaBadanUsaha(String namaBadanUsaha){
		this.namaBadanUsaha = namaBadanUsaha;
	}

	public String getNamaBadanUsaha(){
		return namaBadanUsaha;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public void setBadanUsahaId(Object badanUsahaId){
		this.badanUsahaId = badanUsahaId;
	}

	public Object getBadanUsahaId(){
		return badanUsahaId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"provinsi = '" + provinsi + '\'' + 
			",kota = '" + kota + '\'' + 
			",namaBadanUsaha = '" + namaBadanUsaha + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",badanUsahaId = '" + badanUsahaId + '\'' + 
			",id = '" + id + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}