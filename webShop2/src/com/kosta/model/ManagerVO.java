package com.kosta.model;

public class ManagerVO {
	int manager_id;
	String fullname;
	public ManagerVO() {
		super();
	}
	public ManagerVO(int manager_id, String fullname) {
		super();
		this.manager_id = manager_id;
		this.fullname = fullname;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
