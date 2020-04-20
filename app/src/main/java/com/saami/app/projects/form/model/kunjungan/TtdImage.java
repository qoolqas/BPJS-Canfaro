package com.saami.app.projects.form.model.kunjungan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TtdImage implements Parcelable {

	@SerializedName("raw")
	private String raw;

	@SerializedName("url")
	private String url;

	protected TtdImage(Parcel in) {
		raw = in.readString();
		url = in.readString();
	}

	public static final Creator<TtdImage> CREATOR = new Creator<TtdImage>() {
		@Override
		public TtdImage createFromParcel(Parcel in) {
			return new TtdImage(in);
		}

		@Override
		public TtdImage[] newArray(int size) {
			return new TtdImage[size];
		}
	};

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
    public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(raw);
		parcel.writeString(url);
	}
}