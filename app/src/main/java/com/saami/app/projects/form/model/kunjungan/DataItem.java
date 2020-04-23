package com.saami.app.projects.form.model.kunjungan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("ttdImage")
	private TtdImage ttdImage;

	@SerializedName("image")
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

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

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("TPP")
	private String tPP;

	@SerializedName("createdBy")
	private int createdBy;

	@SerializedName("kendala")
	private String kendala;

	@SerializedName("targetKunjungan")
	private int targetKunjungan;

	@SerializedName("tindakLanjut")
	private String tindakLanjut;

	@SerializedName("targetRecruitment")
	private int targetRecruitment;

	@SerializedName("TPSKP")
	private String tPSKP;

	@SerializedName("badanUsahaId")
	private int badanUsahaId;

	@SerializedName("id")
	private int id;

	@SerializedName("totalRecruitment")
	private int totalRecruitment;

	@SerializedName("status")
	private boolean status;

	@SerializedName("badanUsaha")
	private BadanUsaha badanUsaha;

	@SerializedName("contactBadanUsaha")
	private ContactBadanUsaha contactBadanUsaha;


	public BadanUsaha getBadanUsaha() {
		return badanUsaha;
	}

	public void setBadanUsaha(BadanUsaha badanUsaha) {
		this.badanUsaha = badanUsaha;
	}

	public ContactBadanUsaha getContactBadanUsaha() {
		return contactBadanUsaha;
	}

	public void setContactBadanUsaha(ContactBadanUsaha contactBadanUsaha) {
		this.contactBadanUsaha = contactBadanUsaha;
	}

	public void setTtdImage(TtdImage ttdImage){
		this.ttdImage = ttdImage;
	}

	public TtdImage getTtdImage(){
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

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
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

	public void setTargetKunjungan(int targetKunjungan){
		this.targetKunjungan = targetKunjungan;
	}

	public int getTargetKunjungan(){
		return targetKunjungan;
	}

	public void setTindakLanjut(String tindakLanjut){
		this.tindakLanjut = tindakLanjut;
	}

	public String getTindakLanjut(){
		return tindakLanjut;
	}

	public void setTargetRecruitment(int targetRecruitment){
		this.targetRecruitment = targetRecruitment;
	}

	public int getTargetRecruitment(){
		return targetRecruitment;
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

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"ttdImage = '" + ttdImage + '\'' + 
			",note = '" + note + '\'' + 
			",tMP_BU = '" + tMPBU + '\'' + 
			",reminder = '" + reminder + '\'' + 
			",tPD = '" + tPD + '\'' + 
			",alasan = '" + alasan + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",tPP = '" + tPP + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",kendala = '" + kendala + '\'' + 
			",targetKunjungan = '" + targetKunjungan + '\'' + 
			",tindakLanjut = '" + tindakLanjut + '\'' + 
			",targetRecruitment = '" + targetRecruitment + '\'' + 
			",tPSKP = '" + tPSKP + '\'' + 
			",badanUsahaId = '" + badanUsahaId + '\'' + 
			",id = '" + id + '\'' + 
			",totalRecruitment = '" + totalRecruitment + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	public DataItem() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.ttdImage, flags);
		dest.writeParcelable(this.image, flags);
		dest.writeString(this.note);
		dest.writeString(this.tMPBU);
		dest.writeByte(this.reminder ? (byte) 1 : (byte) 0);
		dest.writeString(this.tPD);
		dest.writeString(this.alasan);
		dest.writeString(this.createdAt);
		dest.writeString(this.tPP);
		dest.writeInt(this.createdBy);
		dest.writeString(this.kendala);
		dest.writeInt(this.targetKunjungan);
		dest.writeString(this.tindakLanjut);
		dest.writeInt(this.targetRecruitment);
		dest.writeString(this.tPSKP);
		dest.writeInt(this.badanUsahaId);
		dest.writeInt(this.id);
		dest.writeInt(this.totalRecruitment);
		dest.writeByte(this.status ? (byte) 1 : (byte) 0);
		dest.writeParcelable(this.badanUsaha, flags);
		dest.writeParcelable(this.contactBadanUsaha, flags);
	}

	protected DataItem(Parcel in) {
		this.ttdImage = in.readParcelable(TtdImage.class.getClassLoader());
		this.image = in.readParcelable(Image.class.getClassLoader());
		this.note = in.readString();
		this.tMPBU = in.readString();
		this.reminder = in.readByte() != 0;
		this.tPD = in.readString();
		this.alasan = in.readString();
		this.createdAt = in.readString();
		this.tPP = in.readString();
		this.createdBy = in.readInt();
		this.kendala = in.readString();
		this.targetKunjungan = in.readInt();
		this.tindakLanjut = in.readString();
		this.targetRecruitment = in.readInt();
		this.tPSKP = in.readString();
		this.badanUsahaId = in.readInt();
		this.id = in.readInt();
		this.totalRecruitment = in.readInt();
		this.status = in.readByte() != 0;
		this.badanUsaha = in.readParcelable(BadanUsaha.class.getClassLoader());
		this.contactBadanUsaha = in.readParcelable(ContactBadanUsaha.class.getClassLoader());
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
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