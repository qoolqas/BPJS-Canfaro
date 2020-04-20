package com.saami.app.projects.form.model.kunjungan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BadanUsaha implements Parcelable {

	@SerializedName("ttdImage")
	private TtdImage ttdImage;

	@SerializedName("keterangan")
	private String keterangan;

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

	@SerializedName("bidangUsaha")
	private String bidangUsaha;

	@SerializedName("phone")
	private String phone;

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

	public void setBidangUsaha(String bidangUsaha){
		this.bidangUsaha = bidangUsaha;
	}

	public String getBidangUsaha(){
		return bidangUsaha;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
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
			"BadanUsaha{" + 
			"ttdImage = '" + ttdImage + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",address = '" + address + '\'' + 
			",jumlahKeluargaTerdaftar = '" + jumlahKeluargaTerdaftar + '\'' + 
			",pesertaJKNOrKIS = '" + pesertaJKNOrKIS + '\'' + 
			",jumlahKaryawanTerdaftar = '" + jumlahKaryawanTerdaftar + '\'' + 
			",jumlahKeluarga = '" + jumlahKeluarga + '\'' + 
			",bidangUsaha = '" + bidangUsaha + '\'' + 
			",phone = '" + phone + '\'' + 
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
		dest.writeParcelable( this.ttdImage, flags);
		dest.writeString(this.keterangan);
		dest.writeString(this.address);
		dest.writeInt(this.jumlahKeluargaTerdaftar);
		dest.writeByte(this.pesertaJKNOrKIS ? (byte) 1 : (byte) 0);
		dest.writeInt(this.jumlahKaryawanTerdaftar);
		dest.writeInt(this.jumlahKeluarga);
		dest.writeString(this.bidangUsaha);
		dest.writeString(this.phone);
		dest.writeByte(this.sosialisasiBPJS ? (byte) 1 : (byte) 0);
		dest.writeString(this.name);
		dest.writeInt(this.jumlahKaryawan);
		dest.writeInt(this.id);
		dest.writeByte(this.asuransiKesehatan ? (byte) 1 : (byte) 0);
		dest.writeString(this.email);
	}

	public BadanUsaha() {
	}

	protected BadanUsaha(Parcel in) {
		this.ttdImage = in.readParcelable(TtdImage.class.getClassLoader());
		this.keterangan = in.readString();
		this.address = in.readString();
		this.jumlahKeluargaTerdaftar = in.readInt();
		this.pesertaJKNOrKIS = in.readByte() != 0;
		this.jumlahKaryawanTerdaftar = in.readInt();
		this.jumlahKeluarga = in.readInt();
		this.bidangUsaha = in.readString();
		this.phone = in.readString();
		this.sosialisasiBPJS = in.readByte() != 0;
		this.name = in.readString();
		this.jumlahKaryawan = in.readInt();
		this.id = in.readInt();
		this.asuransiKesehatan = in.readByte() != 0;
		this.email = in.readString();
	}

	public static final Parcelable.Creator<BadanUsaha> CREATOR = new Parcelable.Creator<BadanUsaha>() {
		@Override
		public BadanUsaha createFromParcel(Parcel source) {
			return new BadanUsaha(source);
		}

		@Override
		public BadanUsaha[] newArray(int size) {
			return new BadanUsaha[size];
		}
	};
}