package com.saami.app.projects.form.model.alamat.post;

import com.google.gson.annotations.SerializedName;

public class AlamatPostResponse{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("kota")
	private String kota;

	@SerializedName("namaBadanUsaha")
	private String namaBadanUsaha;

	@SerializedName("kecamatan")
	private String kecamatan;

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

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
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
			"AlamatPostResponse{" + 
			"provinsi = '" + provinsi + '\'' + 
			",kota = '" + kota + '\'' + 
			",namaBadanUsaha = '" + namaBadanUsaha + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}