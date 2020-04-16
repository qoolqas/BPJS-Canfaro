package com.saami.app.projects.form.model.login;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("image")
	private Object image;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("lastLogin")
	private String lastLogin;

	@SerializedName("gender")
	private int gender;

	@SerializedName("phoneVerified")
	private int phoneVerified;

	@SerializedName("birth")
	private String birth;

	@SerializedName("point")
	private int point;

	@SerializedName("NPP")
	private String nPP;

	@SerializedName("token")
	private String token;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("emailVerified")
	private int emailVerified;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("phone")
	private Object phone;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;


	public void setImage(Object image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastLogin(String lastLogin){
		this.lastLogin = lastLogin;
	}

	public String getLastLogin(){
		return lastLogin;
	}

	public void setGender(int gender){
		this.gender = gender;
	}

	public int getGender(){
		return gender;
	}

	public void setPhoneVerified(int phoneVerified){
		this.phoneVerified = phoneVerified;
	}

	public int getPhoneVerified(){
		return phoneVerified;
	}

	public void setBirth(String birth){
		this.birth = birth;
	}

	public String getBirth(){
		return birth;
	}

	public void setPoint(int point){
		this.point = point;
	}

	public int getPoint(){
		return point;
	}

	public void setNPP(String nPP){
		this.nPP = nPP;
	}

	public String getNPP(){
		return nPP;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmailVerified(int emailVerified){
		this.emailVerified = emailVerified;
	}

	public int getEmailVerified(){
		return emailVerified;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPhone(Object phone){
		this.phone = phone;
	}

	public Object getPhone(){
		return phone;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"image = '" + image + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",lastLogin = '" + lastLogin + '\'' + 
			",gender = '" + gender + '\'' + 
			",phoneVerified = '" + phoneVerified + '\'' + 
			",birth = '" + birth + '\'' + 
			",point = '" + point + '\'' + 
			",nPP = '" + nPP + '\'' + 
			",token = '" + token + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",emailVerified = '" + emailVerified + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}