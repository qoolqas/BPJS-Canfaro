package com.saami.app.projects.form.model.kunjungan.post;

import com.google.gson.annotations.SerializedName;

public class BadanUsaha{

	@SerializedName("ttdImage")
	private TtdImage ttdImage;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("address")
	private String address;

	@SerializedName("jumlahKeluargaTerdaftar")
	private String jumlahKeluargaTerdaftar;

	@SerializedName("pesertaJKNOrKIS")
	private String pesertaJKNOrKIS;

	@SerializedName("jumlahKaryawanTerdaftar")
	private String jumlahKaryawanTerdaftar;

	@SerializedName("jumlahKeluarga")
	private String jumlahKeluarga;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("bidangUsaha")
	private String bidangUsaha;

	@SerializedName("phone")
	private String phone;

	@SerializedName("createdBy")
	private int createdBy;

	@SerializedName("sosialisasiBPJS")
	private String sosialisasiBPJS;

	@SerializedName("name")
	private String name;

	@SerializedName("jumlahKaryawan")
	private String jumlahKaryawan;

	@SerializedName("id")
	private int id;

	@SerializedName("asuransiKesehatan")
	private String asuransiKesehatan;

	@SerializedName("email")
	private String email;

	@SerializedName("updatedAt")
	private String updatedAt;

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

	public void setJumlahKeluargaTerdaftar(String jumlahKeluargaTerdaftar){
		this.jumlahKeluargaTerdaftar = jumlahKeluargaTerdaftar;
	}

	public String getJumlahKeluargaTerdaftar(){
		return jumlahKeluargaTerdaftar;
	}

	public void setPesertaJKNOrKIS(String pesertaJKNOrKIS){
		this.pesertaJKNOrKIS = pesertaJKNOrKIS;
	}

	public String getPesertaJKNOrKIS(){
		return pesertaJKNOrKIS;
	}

	public void setJumlahKaryawanTerdaftar(String jumlahKaryawanTerdaftar){
		this.jumlahKaryawanTerdaftar = jumlahKaryawanTerdaftar;
	}

	public String getJumlahKaryawanTerdaftar(){
		return jumlahKaryawanTerdaftar;
	}

	public void setJumlahKeluarga(String jumlahKeluarga){
		this.jumlahKeluarga = jumlahKeluarga;
	}

	public String getJumlahKeluarga(){
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

	public void setSosialisasiBPJS(String sosialisasiBPJS){
		this.sosialisasiBPJS = sosialisasiBPJS;
	}

	public String getSosialisasiBPJS(){
		return sosialisasiBPJS;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setJumlahKaryawan(String jumlahKaryawan){
		this.jumlahKaryawan = jumlahKaryawan;
	}

	public String getJumlahKaryawan(){
		return jumlahKaryawan;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAsuransiKesehatan(String asuransiKesehatan){
		this.asuransiKesehatan = asuransiKesehatan;
	}

	public String getAsuransiKesehatan(){
		return asuransiKesehatan;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
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
			"BadanUsaha{" + 
			"ttdImage = '" + ttdImage + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",address = '" + address + '\'' + 
			",jumlahKeluargaTerdaftar = '" + jumlahKeluargaTerdaftar + '\'' + 
			",pesertaJKNOrKIS = '" + pesertaJKNOrKIS + '\'' + 
			",jumlahKaryawanTerdaftar = '" + jumlahKaryawanTerdaftar + '\'' + 
			",jumlahKeluarga = '" + jumlahKeluarga + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",bidangUsaha = '" + bidangUsaha + '\'' + 
			",phone = '" + phone + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",sosialisasiBPJS = '" + sosialisasiBPJS + '\'' + 
			",name = '" + name + '\'' + 
			",jumlahKaryawan = '" + jumlahKaryawan + '\'' + 
			",id = '" + id + '\'' + 
			",asuransiKesehatan = '" + asuransiKesehatan + '\'' + 
			",email = '" + email + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}