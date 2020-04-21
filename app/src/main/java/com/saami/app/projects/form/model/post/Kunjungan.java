package com.saami.app.projects.form.model.post;

import com.google.gson.annotations.SerializedName;

public class Kunjungan{

	@SerializedName("ttdImage")
	private String ttdImage;

	@SerializedName("image")
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@SerializedName("note")
	private String note;

	@SerializedName("TMP_BU")
	private String tMPBU;

	@SerializedName("reminder")
	private int reminder;

	@SerializedName("TPP")
	private String tPP;

	@SerializedName("kendala")
	private String kendala;

	@SerializedName("tindakLanjut")
	private String tindakLanjut;

	@SerializedName("TPSKP")
	private String tPSKP;

	@SerializedName("TPD")
	private String tPD;

	@SerializedName("totalRecruitment")
	private String totalRecruitment;

	@SerializedName("alasan")
	private String alasan;

	@SerializedName("status")
	private String status;

	public void setTtdImage(String ttdImage){
		this.ttdImage = ttdImage;
	}

	public String getTtdImage(){
		return ttdImage;
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

	public void setReminder(int reminder){
		this.reminder = reminder;
	}

	public int getReminder(){
		return reminder;
	}

	public void setTPP(String tPP){
		this.tPP = tPP;
	}

	public String getTPP(){
		return tPP;
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

	public void setTPD(String tPD){
		this.tPD = tPD;
	}

	public String getTPD(){
		return tPD;
	}

	public void setTotalRecruitment(String totalRecruitment){
		this.totalRecruitment = totalRecruitment;
	}

	public String getTotalRecruitment(){
		return totalRecruitment;
	}

	public void setAlasan(String alasan){
		this.alasan = alasan;
	}

	public String getAlasan(){
		return alasan;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public String toString(){
		return
				"Kunjungan{" +
						"ttdImage = '" + ttdImage + '\'' +
						",note = '" + note + '\'' +
						",tMP_BU = '" + tMPBU + '\'' +
						",reminder = '" + reminder + '\'' +
						",tPP = '" + tPP + '\'' +
						",kendala = '" + kendala + '\'' +
						",tindakLanjut = '" + tindakLanjut + '\'' +
						",tPSKP = '" + tPSKP + '\'' +
						",tPD = '" + tPD + '\'' +
						",totalRecruitment = '" + totalRecruitment + '\'' +
						",alasan = '" + alasan + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}