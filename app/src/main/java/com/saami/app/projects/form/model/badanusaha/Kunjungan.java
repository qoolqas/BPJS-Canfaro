package com.saami.app.projects.form.model.badanusaha;

import com.google.gson.annotations.SerializedName;

public class Kunjungan{

	@SerializedName("ttdImage")
	private TtdImage ttdImage;

	@SerializedName("image")
	private Image image;

	@SerializedName("note")
	private String note;

	@SerializedName("TMP_BU")
	private String tMPBU;

	@SerializedName("reminder")
	private boolean reminder;

	@SerializedName("TPD")
	private String tPD;

	@SerializedName("alasan")
	private String alasan;

	@SerializedName("TPP")
	private String tPP;

	@SerializedName("createdBy")
	private int createdBy;

	@SerializedName("kendala")
	private String kendala;

	@SerializedName("tindakLanjut")
	private String tindakLanjut;

	@SerializedName("TPSKP")
	private String tPSKP;

	@SerializedName("badanUsahaId")
	private int badanUsahaId;

	@SerializedName("id")
	private int id;

	@SerializedName("totalRecruitment")
	private int totalRecruitment;

	public void setTtdImage(TtdImage ttdImage){
		this.ttdImage = ttdImage;
	}

	public TtdImage getTtdImage(){
		return ttdImage;
	}

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setTMPBU(String tMPBU){
		this.tMPBU = tMPBU;
	}

	public String getTMPBU(){
		return tMPBU;
	}

	public void setReminder(boolean reminder){
		this.reminder = reminder;
	}

	public boolean isReminder(){
		return reminder;
	}

	public void setTPD(String tPD){
		this.tPD = tPD;
	}

	public String getTPD(){
		return tPD;
	}

	public void setAlasan(String alasan){
		this.alasan = alasan;
	}

	public String getAlasan(){
		return alasan;
	}

	public void setTPP(String tPP){
		this.tPP = tPP;
	}

	public String getTPP(){
		return tPP;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setKendala(String kendala){
		this.kendala = kendala;
	}

	public String getKendala(){
		return kendala;
	}

	public void setTindakLanjut(String tindakLanjut){
		this.tindakLanjut = tindakLanjut;
	}

	public String getTindakLanjut(){
		return tindakLanjut;
	}

	public void setTPSKP(String tPSKP){
		this.tPSKP = tPSKP;
	}

	public String getTPSKP(){
		return tPSKP;
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

	public void setTotalRecruitment(int totalRecruitment){
		this.totalRecruitment = totalRecruitment;
	}

	public int getTotalRecruitment(){
		return totalRecruitment;
	}

	@Override
 	public String toString(){
		return 
			"Kunjungan{" + 
			"ttdImage = '" + ttdImage + '\'' + 
			",image = '" + image + '\'' + 
			",note = '" + note + '\'' + 
			",tMP_BU = '" + tMPBU + '\'' + 
			",reminder = '" + reminder + '\'' + 
			",tPD = '" + tPD + '\'' + 
			",alasan = '" + alasan + '\'' + 
			",tPP = '" + tPP + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",kendala = '" + kendala + '\'' + 
			",tindakLanjut = '" + tindakLanjut + '\'' + 
			",tPSKP = '" + tPSKP + '\'' + 
			",badanUsahaId = '" + badanUsahaId + '\'' + 
			",id = '" + id + '\'' + 
			",totalRecruitment = '" + totalRecruitment + '\'' + 
			"}";
		}
}