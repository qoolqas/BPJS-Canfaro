package com.saami.app.projects.form.model.badanusaha;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TtdImage implements Parcelable {

	@SerializedName("raw")
	private String raw;

	@SerializedName("url")
	private String url;

	public void setRaw(String raw){
		this.raw = raw;
	}

	public String getRaw(){
		return raw;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"TtdImage{" + 
			"raw = '" + raw + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.raw);
		dest.writeString(this.url);
	}

	public TtdImage() {
	}

	protected TtdImage(Parcel in) {
		this.raw = in.readString();
		this.url = in.readString();
	}

	public static final Parcelable.Creator<TtdImage> CREATOR = new Parcelable.Creator<TtdImage>() {
		@Override
		public TtdImage createFromParcel(Parcel source) {
			return new TtdImage(source);
		}

		@Override
		public TtdImage[] newArray(int size) {
			return new TtdImage[size];
		}
	};
}