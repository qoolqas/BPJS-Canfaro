package com.saami.app.projects.form.model.alamat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.provinsi);
		dest.writeString(this.kota);
		dest.writeString(this.namaBadanUsaha);
		dest.writeInt(this.createdBy);
		dest.writeString(this.kecamatan);
		dest.writeParcelable((Parcelable) this.badanUsahaId, flags);
		dest.writeInt(this.id);
		dest.writeString(this.alamat);
	}

	public DataItem() {
	}

	protected DataItem(Parcel in) {
		this.provinsi = in.readString();
		this.kota = in.readString();
		this.namaBadanUsaha = in.readString();
		this.createdBy = in.readInt();
		this.kecamatan = in.readString();
		this.badanUsahaId = in.readParcelable(Object.class.getClassLoader());
		this.id = in.readInt();
		this.alamat = in.readString();
	}

	public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel source) {
			return new DataItem(source);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};
}