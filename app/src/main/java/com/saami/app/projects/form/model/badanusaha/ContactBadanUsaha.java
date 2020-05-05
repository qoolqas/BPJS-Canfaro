package com.saami.app.projects.form.model.badanusaha;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ContactBadanUsaha implements Parcelable {

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

	@Override
 	public String toString(){
		return 
			"ContactBadanUsaha{" + 
			"unitKerja = '" + unitKerja + '\'' + 
			",phone = '" + phone + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",jabatan = '" + jabatan + '\'' + 
			",name = '" + name + '\'' + 
			",badanUsahaId = '" + badanUsahaId + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.unitKerja);
		dest.writeString(this.phone);
		dest.writeInt(this.createdBy);
		dest.writeString(this.jabatan);
		dest.writeString(this.name);
		dest.writeInt(this.badanUsahaId);
		dest.writeInt(this.id);
	}

	public ContactBadanUsaha() {
	}

	protected ContactBadanUsaha(Parcel in) {
		this.unitKerja = in.readString();
		this.phone = in.readString();
		this.createdBy = in.readInt();
		this.jabatan = in.readString();
		this.name = in.readString();
		this.badanUsahaId = in.readInt();
		this.id = in.readInt();
	}

	public static final Parcelable.Creator<ContactBadanUsaha> CREATOR = new Parcelable.Creator<ContactBadanUsaha>() {
		@Override
		public ContactBadanUsaha createFromParcel(Parcel source) {
			return new ContactBadanUsaha(source);
		}

		@Override
		public ContactBadanUsaha[] newArray(int size) {
			return new ContactBadanUsaha[size];
		}
	};
}