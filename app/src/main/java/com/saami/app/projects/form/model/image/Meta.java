package com.saami.app.projects.form.model.image;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("message")
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}