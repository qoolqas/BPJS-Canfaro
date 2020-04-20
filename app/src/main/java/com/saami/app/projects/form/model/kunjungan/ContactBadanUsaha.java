package com.saami.app.projects.form.model.kunjungan;

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

	protected ContactBadanUsaha(Parcel in) {
		unitKerja = in.readString();
		phone = in.readString();
		createdBy = in.readInt();
		jabatan = in.readString();
		name = in.readString();
		badanUsahaId = in.readInt();
		id = in.readInt();
	}

	public static final Creator<ContactBadanUsaha> CREATOR = new Creator<ContactBadanUsaha>() {
		@Override
		public ContactBadanUsaha createFromParcel(Parcel in) {
			return new ContactBadanUsaha(in);
		}

		@Override
		public ContactBadanUsaha[] newArray(int size) {
			return new ContactBadanUsaha[size];
		}
	};

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
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(unitKerja);
		parcel.writeString(phone);
		parcel.writeInt(createdBy);
		parcel.writeString(jabatan);
		parcel.writeString(name);
		parcel.writeInt(badanUsahaId);
		parcel.writeInt(id);
	}
}