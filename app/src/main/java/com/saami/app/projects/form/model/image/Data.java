package com.saami.app.projects.form.model.image;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("filename")
	private String filename;

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"filename = '" + filename + '\'' + 
			"}";
		}
}