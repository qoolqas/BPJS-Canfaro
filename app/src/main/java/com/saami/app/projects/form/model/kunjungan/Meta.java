package com.saami.app.projects.form.model.kunjungan;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("total")
	private int total;

	@SerializedName("lastPage")
	private int lastPage;

	@SerializedName("success")
	private boolean success;

	@SerializedName("pageSize")
	private int pageSize;

	@SerializedName("page")
	private int page;

	@SerializedName("message")
	private String message;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setLastPage(int lastPage){
		this.lastPage = lastPage;
	}

	public int getLastPage(){
		return lastPage;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}

	public int getPageSize(){
		return pageSize;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

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
			"total = '" + total + '\'' + 
			",lastPage = '" + lastPage + '\'' + 
			",success = '" + success + '\'' + 
			",pageSize = '" + pageSize + '\'' + 
			",page = '" + page + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}