package com.saami.app.projects.form.model.badanusaha;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("ttdImage")
	private TtdImage ttdImage;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("kunjungan")
	private Kunjungan kunjungan;

	@SerializedName("address")
	private String address;

	@SerializedName("jumlahKeluargaTerdaftar")
	private int jumlahKeluargaTerdaftar;

	@SerializedName("pesertaJKNOrKIS")
	private boolean pesertaJKNOrKIS;

	@SerializedName("jumlahKaryawanTerdaftar")
	private int jumlahKaryawanTerdaftar;

	@SerializedName("jumlahKeluarga")
	private int jumlahKeluarga;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("bidangUsaha")
	private String bidangUsaha;

	@SerializedName("contactBadanUsaha")
	private ContactBadanUsaha contactBadanUsaha;

	@SerializedName("phone")
	private String phone;

	@SerializedName("createdBy")
	private int createdBy;

	@SerializedName("sosialisasiBPJS")
	private boolean sosialisasiBPJS;

	@SerializedName("name")
	private String name;

	@SerializedName("jumlahKaryawan")
	private int jumlahKaryawan;

	@SerializedName("id")
	private int id;

	@SerializedName("asuransiKesehatan")
	private boolean asuransiKesehatan;

	@SerializedName("email")
	private String email;

	public void setTtdImage(TtdImage ttdImage){
		this.ttdImage = ttdImage;
	}

	public TtdImage getTtdImage(){
		return ttdImage;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setKunjungan(Kunjungan kunjungan){
		this.kunjungan = kunjungan;
	}

	public Kunjungan getKunjungan(){
		return kunjungan;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setJumlahKeluargaTerdaftar(int jumlahKeluargaTerdaftar){
		this.jumlahKeluargaTerdaftar = jumlahKeluargaTerdaftar;
	}

	public int getJumlahKeluargaTerdaftar(){
		return jumlahKeluargaTerdaftar;
	}

	public void setPesertaJKNOrKIS(boolean pesertaJKNOrKIS){
		this.pesertaJKNOrKIS = pesertaJKNOrKIS;
	}

	public boolean isPesertaJKNOrKIS(){
		return pesertaJKNOrKIS;
	}

	public void setJumlahKaryawanTerdaftar(int jumlahKaryawanTerdaftar){
		this.jumlahKaryawanTerdaftar = jumlahKaryawanTerdaftar;
	}

	public int getJumlahKaryawanTerdaftar(){
		return jumlahKaryawanTerdaftar;
	}

	public void setJumlahKeluarga(int jumlahKeluarga){
		this.jumlahKeluarga = jumlahKeluarga;
	}

	public int getJumlahKeluarga(){
		return jumlahKeluarga;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setBidangUsaha(String bidangUsaha){
		this.bidangUsaha = bidangUsaha;
	}

	public String getBidangUsaha(){
		return bidangUsaha;
	}

	public void setContactBadanUsaha(ContactBadanUsaha contactBadanUsaha){
		this.contactBadanUsaha = contactBadanUsaha;
	}

	public ContactBadanUsaha getContactBadanUsaha(){
		return contactBadanUsaha;
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

	public void setSosialisasiBPJS(boolean sosialisasiBPJS){
		this.sosialisasiBPJS = sosialisasiBPJS;
	}

	public boolean isSosialisasiBPJS(){
		return sosialisasiBPJS;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setJumlahKaryawan(int jumlahKaryawan){
		this.jumlahKaryawan = jumlahKaryawan;
	}

	public int getJumlahKaryawan(){
		return jumlahKaryawan;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAsuransiKesehatan(boolean asuransiKesehatan){
		this.asuransiKesehatan = asuransiKesehatan;
	}

	public boolean isAsuransiKesehatan(){
		return asuransiKesehatan;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"ttdImage = '" + ttdImage + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",kunjungan = '" + kunjungan + '\'' + 
			",address = '" + address + '\'' + 
			",jumlahKeluargaTerdaftar = '" + jumlahKeluargaTerdaftar + '\'' + 
			",pesertaJKNOrKIS = '" + pesertaJKNOrKIS + '\'' + 
			",jumlahKaryawanTerdaftar = '" + jumlahKaryawanTerdaftar + '\'' + 
			",jumlahKeluarga = '" + jumlahKeluarga + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",bidangUsaha = '" + bidangUsaha + '\'' + 
			",contactBadanUsaha = '" + contactBadanUsaha + '\'' + 
			",phone = '" + phone + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",sosialisasiBPJS = '" + sosialisasiBPJS + '\'' + 
			",name = '" + name + '\'' + 
			",jumlahKaryawan = '" + jumlahKaryawan + '\'' + 
			",id = '" + id + '\'' + 
			",asuransiKesehatan = '" + asuransiKesehatan + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.ttdImage, flags);
		dest.writeString(this.keterangan);
		dest.writeParcelable(this.kunjungan, flags);
		dest.writeString(this.address);
		dest.writeInt(this.jumlahKeluargaTerdaftar);
		dest.writeByte(this.pesertaJKNOrKIS ? (byte) 1 : (byte) 0);
		dest.writeInt(this.jumlahKaryawanTerdaftar);
		dest.writeInt(this.jumlahKeluarga);
		dest.writeString(this.createdAt);
		dest.writeString(this.bidangUsaha);
		dest.writeParcelable(this.contactBadanUsaha, flags);
		dest.writeString(this.phone);
		dest.writeInt(this.createdBy);
		dest.writeByte(this.sosialisasiBPJS ? (byte) 1 : (byte) 0);
		dest.writeString(this.name);
		dest.writeInt(this.jumlahKaryawan);
		dest.writeInt(this.id);
		dest.writeByte(this.asuransiKesehatan ? (byte) 1 : (byte) 0);
		dest.writeString(this.email);
	}

	public DataItem() {
	}

	protected DataItem(Parcel in) {
		this.ttdImage = in.readParcelable(TtdImage.class.getClassLoader());
		this.keterangan = in.readString();
		this.kunjungan = in.readParcelable(Kunjungan.class.getClassLoader());
		this.address = in.readString();
		this.jumlahKeluargaTerdaftar = in.readInt();
		this.pesertaJKNOrKIS = in.readByte() != 0;
		this.jumlahKaryawanTerdaftar = in.readInt();
		this.jumlahKeluarga = in.readInt();
		this.createdAt = in.readString();
		this.bidangUsaha = in.readString();
		this.contactBadanUsaha = in.readParcelable(ContactBadanUsaha.class.getClassLoader());
		this.phone = in.readString();
		this.createdBy = in.readInt();
		this.sosialisasiBPJS = in.readByte() != 0;
		this.name = in.readString();
		this.jumlahKaryawan = in.readInt();
		this.id = in.readInt();
		this.asuransiKesehatan = in.readByte() != 0;
		this.email = in.readString();
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