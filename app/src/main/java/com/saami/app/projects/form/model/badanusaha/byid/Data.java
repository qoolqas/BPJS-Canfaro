package com.saami.app.projects.form.model.badanusaha.byid;

import com.google.gson.annotations.SerializedName;

public class Data{

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
			"Data{" + 
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
}